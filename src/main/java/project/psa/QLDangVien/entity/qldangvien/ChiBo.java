package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "chibo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChiBo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenchibo;
    private String danguycaptren;
    private String diachi;
    private String bithu;
    private String phobithu;
    private String ngaythanhlap;
    private String ghichu;
    private String trangthai;

    @OneToMany(mappedBy = "chibo")
    private List<XepLoai> xeploais;
    @OneToMany(mappedBy = "chibo")
    private List<DangVien> dangViens;
}
