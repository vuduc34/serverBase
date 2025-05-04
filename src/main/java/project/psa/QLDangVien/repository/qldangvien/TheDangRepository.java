package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.TheDang;
import project.psa.QLDangVien.entity.qldangvien.TinTuc;
import project.psa.QLDangVien.model.qldangvien.thedangDTO;

import java.util.List;

@Repository
public interface TheDangRepository extends JpaRepository<TheDang, Long> {
    @Query( value = "SELECT  * FROM thedang where dangvien_id = :dangvien_id ", nativeQuery = true)
    TheDang findByDangvienId(@Param("dangvien_id") Long dangvien_id);
    TheDang findTheDangById(Long id);

    @Query("SELECT new project.psa.QLDangVien.model.qldangvien.thedangDTO( " +
            "t.id, t.mathe, t.noicapthe, t.ngaycap, d.hoten, d.ngayvaodang, d.ngaychinhthuc) " +
            "FROM TheDang t JOIN DangVien d ON t.dangvien.id = d.id " +
            "WHERE t.dangvien.id = :dangvien_id")
    thedangDTO findDetailByDangvienId(@Param("dangvien_id") Long dangvien_id);

    @Query("SELECT new project.psa.QLDangVien.model.qldangvien.thedangDTO( " +
            "t.id, t.mathe, t.noicapthe, t.ngaycap, d.hoten, d.ngayvaodang, d.ngaychinhthuc) " +
            "FROM TheDang t JOIN DangVien d ON t.dangvien.id = d.id ")
    List<thedangDTO> findAllTheDang();

}
