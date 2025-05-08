package project.psa.QLDangVien.model.qldangvien;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class pheduyetDTO {
    private Long id;
    private String loaipheduyet;
    private String nguoipheduyet;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigianguipheduyet;
    private String ghichu;
    private String tendangvien;
    private String tieude;
    private List<String> tenhoso;
    private String trangthai;
}
