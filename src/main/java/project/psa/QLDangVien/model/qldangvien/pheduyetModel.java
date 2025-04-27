package project.psa.QLDangVien.model.qldangvien;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class pheduyetModel {
    private String loaipheduyet;
    private String nguoipheduyet;
    private List<Long> listHosoId;
    private Long dangvienId;
    private Long tintucId;
    private String ghichu;
}
