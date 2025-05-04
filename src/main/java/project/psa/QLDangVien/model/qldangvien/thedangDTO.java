package project.psa.QLDangVien.model.qldangvien;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class thedangDTO {
    private Long thedangId;
    private String mathe;
    private String noicapthe;
    private String ngaycap;
    private String hoten;
    private String ngayvaodang;
    private String ngaychinhthuc;
}
