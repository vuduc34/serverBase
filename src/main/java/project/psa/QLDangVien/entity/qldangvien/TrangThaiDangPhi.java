package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

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
    private String nguoixacnhan;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigianxacnhan;
}
