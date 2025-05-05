package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.auth.account;
import project.psa.QLDangVien.entity.qldangvien.ChiBo;

import java.util.List;

@Repository
public interface ChiBoRepository extends JpaRepository<ChiBo, Long> {
    ChiBo findChiBoById(Long id);
    @Query( value = "SELECT  * FROM chibo where trangthai = 'hoatdong'", nativeQuery = true)
    List<ChiBo> findChiBoHoatDong();

    @Query("SELECT COUNT(c) FROM ChiBo c")
    Long countChiBo();
}
