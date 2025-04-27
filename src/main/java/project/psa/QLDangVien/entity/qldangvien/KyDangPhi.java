package project.psa.QLDangVien.entity.qldangvien;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "kydangphi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KyDangPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ten;
    private Double sotien;
}
