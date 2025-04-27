package project.psa.QLDangVien.entity.qldangvien;

import lombok.Data;
import java.io.Serializable;

@Data
public class TrangThaiDangPhiId implements Serializable {
    private Long kydangphi;
    private Long dangvien;
}
