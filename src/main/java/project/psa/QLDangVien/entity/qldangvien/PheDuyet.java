package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.spi.LocaleNameProvider;

@Entity
@Table(name = "pheduyet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PheDuyet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loaipheduyet;
    private String nguoipheduyet;
    private Long dangvienId;
    private Long tintucId;
    @Convert(converter = ListToStringConverter.class)
    private List<Long> listHosoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigianguipheduyet;
    private String trangthai;
    private String ghichu;
}
