package project.psa.QLDangVien.entity.qldangvien;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "thedang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheDang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mathe;
    private String noicapthe;
    private LocalDate ngaycap;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dangvien_id", referencedColumnName = "id")
    private DangVien dangvien;
}
