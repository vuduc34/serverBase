package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "hosodang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HosoDang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taphoso;
    private String loaihoso;
    private String fileUrl;
    private String trangthai;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigiantao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigianpheduyet;
    private String nguoipheduyet;
    private String ghichu;

    @ManyToOne
    @JoinColumn(name = "dangvien_id")
    @JsonIgnore
    private DangVien dangvien;
}
