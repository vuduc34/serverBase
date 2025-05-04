package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "quyetdinh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuyetDinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loaiquyetdinh;
    private String nam;
    private String fileUrl;
    private String tenquyetdinh;

    @ManyToOne
    @JoinColumn(name = "dangvien_id")
    @JsonIgnore
    private DangVien dangvien;
}
