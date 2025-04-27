package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tintuc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TinTuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tieude;
    private String mota;
    private String nguoitao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigiantao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigianpheduyet;
    private String url;
    private String trangthai;
    private String nguoipheduyet;

    @OneToMany(mappedBy = "tintuc")
    private List<NoiDungTin> noidungtins;
}
