package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.PheDuyet;

import java.util.List;

@Repository
public interface PheDuyetRepository extends JpaRepository<PheDuyet, Long> {
    @Query( value = "SELECT  * FROM pheduyet where nguoipheduyet = :nguoipheduyet order by trangthai desc, thoigianguipheduyet desc ", nativeQuery = true)
    List<PheDuyet> findByUsername(@Param("nguoipheduyet") String nguoipheduyet);
    PheDuyet findPheDuyetById(Long id);
}