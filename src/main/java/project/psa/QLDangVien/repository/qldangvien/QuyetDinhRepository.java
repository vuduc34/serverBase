package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.HosoDang;
import project.psa.QLDangVien.entity.qldangvien.QuyetDinh;

import java.util.List;

@Repository
public interface QuyetDinhRepository extends JpaRepository<QuyetDinh, Long> {
    QuyetDinh findQuyetDinhById(Long id);

    @Query( value = "SELECT  * FROM quyetdinh where dangvien_id = :dangvien_id order by nam desc,id desc ", nativeQuery = true)
    List<QuyetDinh> findByDangvienId(@Param("dangvien_id") Long dangvien_id);
}