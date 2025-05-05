package project.psa.QLDangVien.model.qldangvien;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeResponse {
    private Long tongSoDangVien;
    private Long soNam;
    private Long soNu;

    private Long tongSoChiBo;
    private Map<String, Long> dangVienTheoChiBo;

    private Long tongSoTinTuc;
    private Long soTinTucDaDuyet;

    private Long tongSoTaiKhoan;
    private Map<String, Long> taiKhoanTheoRole;
}
