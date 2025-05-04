package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.TinTuc;
import project.psa.QLDangVien.entity.qldangvien.XepLoai;

import java.util.List;

@Repository
public interface TinTucRepository extends JpaRepository<TinTuc, Long> {
    TinTuc findTinTucById(Long id);
    boolean existsById(Long id);

    @Query( value = "SELECT  * FROM tintuc order by thoigiantao desc ", nativeQuery = true)
    List<TinTuc> findAllTintuc();

    @Query( value = "SELECT  * FROM tintuc where trangthai = 'approved' order by thoigianpheduyet desc ", nativeQuery = true)
    List<TinTuc> findApproved();
}