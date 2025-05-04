package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String ngaycap;
    @OneToOne
    @JoinColumn(name = "dangvien_id", referencedColumnName = "id")
    @JsonIgnore
    private DangVien dangvien;
}
