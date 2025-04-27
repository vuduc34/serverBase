package project.psa.QLDangVien.entity.qldangvien;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "noidungtin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoiDungTin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer thutu;
    private String noidung;
    private String url;

    @ManyToOne
    @JoinColumn(name = "tintuc_id")
    private TinTuc tintuc;
}
