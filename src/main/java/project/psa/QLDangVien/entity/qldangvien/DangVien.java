package project.psa.QLDangVien.entity.qldangvien;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dangvien")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hoten;
    private String ngaysinh;
    private String gioitinh;
    private String quequan;
    private String dantoc;
    private String trinhdovanhoa;
    private String noihiennay;
    private String chuyennmon;
    private String ngayvaodang;
    private String ngaychinhthuc;
    private String nguoigioithieu1;
    private String nguoigioithieu2;
    private String chucvuchinhquyen;
    private String chucvuchibo;
    private String chucvudanguy;
    private String chucvudoanthe;
    private String noisinhhoatdang;
    private String chucdanh;
    private String trinhdongoaingu;
    private String trinhdochinhtri;
    private String trangthaithongtin;
    private String trangthaidangvien;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss.SSSSSS")
    private LocalDateTime thoigianpheduyet;
    private String nguoipheduyet;

    @OneToMany(mappedBy = "dangvien")
    @JsonIgnore
    private List<HosoDang> hosodangs;

    @OneToMany(mappedBy = "dangvien")
    @JsonIgnore
    private List<QuyetDinh> quyetdinhs;

    @OneToMany(mappedBy = "dangvien")
    @JsonIgnore
    private List<TrangThaiDangPhi> trangthaidangphis;

    @OneToOne(mappedBy = "dangvien")
    private TheDang thedang;

    @ManyToOne
    @JoinColumn(name = "chibo_id")
    @JsonIgnore
    private ChiBo chibo;
}
