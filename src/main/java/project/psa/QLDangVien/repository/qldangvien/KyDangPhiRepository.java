package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.KyDangPhi;
import project.psa.QLDangVien.entity.qldangvien.TinTuc;

import java.util.List;

@Repository
public interface KyDangPhiRepository extends JpaRepository<KyDangPhi, Long> {
    KyDangPhi findKyDangPhiById(Long id);
    @Query( value = "SELECT  * FROM kydangphi order by id desc ", nativeQuery = true)
    List<KyDangPhi> findAllKydangphi();

    boolean  existsByTen(String ten);
}