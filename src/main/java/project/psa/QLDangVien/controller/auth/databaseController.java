package project.psa.QLDangVien.controller.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.psa.QLDangVien.common.constant;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.psa.QLDangVien.model.ResponMessage;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(constant.API.PREFIX+"/database")
public class databaseController {
    @Value("${spring.datasource.username}")
    private  String user;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;


    @PostMapping("/backup")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> backupDatabaseJdbc() {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {
            File backupFile = File.createTempFile("backup-data-", ".sql");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(backupFile))) {
                DatabaseMetaData metaData = conn.getMetaData();
                ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

                // Dùng Set để tránh ghi trùng bảng
                Set<String> backedUpTables = new HashSet<>();

                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");

                    // Kiểm tra xem bảng đã được backup chưa
                    if (backedUpTables.contains(tableName)) {
                        continue;
                    }

                    // Kiểm tra bảng có truy cập được không
                    try (Statement testStmt = conn.createStatement()) {
                        testStmt.execute("SELECT 1 FROM `" + tableName + "` LIMIT 1");
                    } catch (SQLException e) {
                        System.out.println("Skip table name: " + tableName);
                        continue;
                    }

                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT * FROM `" + tableName + "`")) {

                        ResultSetMetaData rsMeta = rs.getMetaData();
                        int columnCount = rsMeta.getColumnCount();

                        while (rs.next()) {
                            StringBuilder insert = new StringBuilder("INSERT  INTO `" + tableName + "` VALUES (");
                            for (int i = 1; i <= columnCount; i++) {
                                Object value = rs.getObject(i);
                                if (value == null) {
                                    insert.append("NULL");
                                } else if (value instanceof LocalDateTime) {
                                    LocalDateTime localDateTimeValue = (LocalDateTime) value;
                                    String formattedDate = localDateTimeValue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
                                    insert.append("'").append(formattedDate).append("'");
                                } else if (value instanceof String) {
                                    insert.append("'").append(value.toString().replace("'", "''")).append("'");
                                } else {
                                    insert.append(value);
                                }
                                if (i < columnCount) insert.append(", ");
                            }
                            insert.append(") ON DUPLICATE KEY UPDATE ");

                            List<String> columnNames = new ArrayList<>();
                            for (int i = 1; i <= columnCount; i++) {
                                columnNames.add(rsMeta.getColumnName(i));
                            }

                            for (int i = 1; i <= columnCount; i++) {
                                String colName = columnNames.get(i - 1);
                                insert.append("`").append(colName).append("` = VALUES(`").append(colName).append("`)");
                                if (i < columnCount) insert.append(", ");
                            }

                            insert.append(";\n");
                            writer.write(insert.toString());
                        }

                        writer.write("\n");

                        // ✅ Ghi nhận đã backup bảng này
                        backedUpTables.add(tableName);

                    } catch (SQLException e) {
                        System.out.println("❌ Không thể SELECT từ bảng " + tableName + ": " + e.getMessage());
                    }
                }
            }

            byte[] data = Files.readAllBytes(backupFile.toPath());
            backupFile.delete();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=jdbc-backup-" + Instant.now() + ".sql")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(data);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Backup JDBC Error: " + e.getMessage());
        }
    }



    @PostMapping(value = "/restore",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponMessage restoreDatabase(@RequestParam("file") MultipartFile file) {
        ResponMessage responMessage = new ResponMessage();
        Connection connection = null;
        try {
            // B1. Lưu file upload vào hệ thống tạm thời
            File uploadedFile = File.createTempFile("uploaded-", ".sql");
            file.transferTo(uploadedFile);

            // B2. Tạo kết nối JDBC
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            connection.setAutoCommit(false);  // Disable auto commit để thực thi các câu lệnh SQL một cách thủ công

            // B3. Tắt kiểm tra khóa ngoại để tránh lỗi vi phạm khóa ngoại trong quá trình restore
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");
            }

            // B4. Đọc và thực thi từng câu lệnh SQL từ file
            List<String> sqlStatements = readSqlFile(uploadedFile);
            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    try (Statement stmt = connection.createStatement()) {
                        stmt.executeUpdate(sql);
                    }
                }
            }

            // B5. Bật lại kiểm tra khóa ngoại sau khi restore xong
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 1;");
            }

            // B6. Commit các thay đổi vào cơ sở dữ liệu
            connection.commit();
            uploadedFile.delete();  // Xóa file tạm thời
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);

            return responMessage;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();  // Rollback nếu có lỗi
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            responMessage.setMessage(e.getMessage());
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);

            return responMessage;
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();  // Đảm bảo đóng kết nối
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> readSqlFile(File file) throws IOException {
        List<String> sqlStatements = new ArrayList<>();
        StringBuilder currentStatement = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Loại bỏ các dòng không phải câu lệnh SQL (dòng cảnh báo hoặc chú thích)
                if (line.startsWith("mysqldump:") || line.startsWith("--")) {
                    continue;  // Bỏ qua dòng này
                }

                line = line.trim();
                if (line.endsWith(";")) {
                    // Nếu câu lệnh SQL kết thúc bằng dấu chấm phẩy, thực thi câu lệnh
                    currentStatement.append(line, 0, line.length());
                    sqlStatements.add(currentStatement.toString().trim());
                    currentStatement.setLength(0);  // Reset builder để tiếp tục câu lệnh tiếp theo
                } else {
                    // Nếu chưa kết thúc, tiếp tục nối các dòng lại
                    currentStatement.append(line).append(" ");
                }
            }
        }

        return sqlStatements;
    }



}
