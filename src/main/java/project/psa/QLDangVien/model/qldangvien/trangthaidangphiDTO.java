package project.psa.QLDangVien.model.qldangvien;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class trangthaidangphiDTO {
    private String dangvienId;
    private String kydangphiId;
    private String hotenDangvien;
    private String trangthai;
    private String nguoixacnhan;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigianxacnhan;
}
