package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.DangVien;

import java.util.List;

@Repository
public interface DangVienRepository extends JpaRepository<DangVien, Long> {
    @Query( value = "SELECT  * FROM dangvien where id = :id", nativeQuery = true)
    DangVien findDangVienById(@Param("id") Long id);

    @Query( value = "SELECT  * FROM dangvien where trangthaithongtin = 'approved'", nativeQuery = true)
    List<DangVien> findDangViensApproved();
    boolean existsById(Long id);

    @Query( value = "SELECT  * FROM dangvien where chibo_id = :chibo_id", nativeQuery = true)
    List<DangVien> findDangVienByChiBoId(@Param("chibo_id") Long chibo_id);

    @Query( value = "select d.id,d.hoten,c.tenchibo,t.mathe from dangvien as d join chibo as c\n" +
            "on d.chibo_id = c.id join thedang t on d.id = t.dangvien_id\n" +
            "where d.trangthaithongtin = 'approved' and ( d.hoten like %:text% or c.tenchibo like %:text% or t.mathe " +
            "like %:text% or d.chucvuchibo like %:text% )" , nativeQuery = true)
    List<Object[]> findDangVienByText(@Param("text") String text);
}
