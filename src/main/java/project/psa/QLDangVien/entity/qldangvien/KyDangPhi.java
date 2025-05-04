package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

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
    private Long sotien;
    private String nguoitao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigiantao;
}
