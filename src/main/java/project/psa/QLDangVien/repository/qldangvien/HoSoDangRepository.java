package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.HosoDang;
import java.util.List;

@Repository
public interface HoSoDangRepository extends JpaRepository<HosoDang, Long> {
    boolean existsById(Long id);
    HosoDang findHosoDangById(Long id);
    @Query( value = "SELECT  * FROM hosodang where dangvien_id = :dangvien_id order by taphoso asc, loaihoso asc,thoigiantao desc ", nativeQuery = true)
    List<HosoDang> findHosoDangByDangvienId(@Param("dangvien_id") Long dangvien_id);

    @Query( value = "SELECT  * FROM hosodang where trangthai = 'approved' and dangvien_id = :dangvien_id order by taphoso asc, loaihoso asc,thoigianpheduyet desc ", nativeQuery = true)
    List<HosoDang> findHosoDangApprovedByDangvienId(@Param("dangvien_id") Long dangvien_id);

    @Query("SELECT h FROM HosoDang h WHERE h.id IN :ids")
    List<HosoDang> findByIdIn(@Param("ids") List<Long> ids);





}
