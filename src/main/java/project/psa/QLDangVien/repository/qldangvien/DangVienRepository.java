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

    @Query( value = "select d.* from dangvien as d join chibo as c\n" +
            "on d.chibo_id = c.id left join thedang t on d.id = t.dangvien_id\n" +
            "where  d.hoten like %:text% or c.tenchibo like %:text% or t.mathe " +
            "like %:text% or d.chucvuchibo like %:text% " , nativeQuery = true)
    List<DangVien> findDangVienByText(@Param("text") String text);

//    Thống kê
    @Query("SELECT COUNT(d) FROM DangVien d")
    Long countDangVien();

    @Query("SELECT COUNT(d) FROM DangVien d WHERE d.gioitinh = 'Nam'")
    Long countNam();

    @Query("SELECT COUNT(d) FROM DangVien d WHERE d.gioitinh = 'Nữ'")
    Long countNu();

    @Query("SELECT c.tenchibo, COUNT(d.id) FROM DangVien d JOIN d.chibo c GROUP BY c.tenchibo")
    List<Object[]> countDangVienTheoChiBo();


}
