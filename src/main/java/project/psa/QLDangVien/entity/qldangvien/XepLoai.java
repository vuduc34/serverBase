package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "xeploai")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XepLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String xeploai;
    private String nam;
    private String nguoitao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigiantao;

    @ManyToOne
    @JoinColumn(name = "chibo_id")
    @JsonIgnore
    private ChiBo chibo;
}
