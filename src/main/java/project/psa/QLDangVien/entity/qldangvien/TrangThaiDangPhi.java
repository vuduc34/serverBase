package project.psa.QLDangVien.entity.qldangvien;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "trangthaidangphi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TrangThaiDangPhiId.class)
public class TrangThaiDangPhi {
    @Id
    @ManyToOne
    @JoinColumn(name = "kydangphi_id")
    private KyDangPhi kydangphi;

    @Id
    @ManyToOne
    @JoinColumn(name = "dangvien_id")
    private DangVien dangvien;

    private String trangthai;
}
