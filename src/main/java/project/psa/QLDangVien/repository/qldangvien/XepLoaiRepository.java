package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.XepLoai;

import java.util.List;

@Repository
public interface XepLoaiRepository extends JpaRepository<XepLoai, Long> {
    @Query( value = "SELECT  * FROM xeploai where chibo_id = :chibo_id and nam=:nam", nativeQuery = true)
    XepLoai findByChiboIdAndNam(@Param("chibo_id") Long chibo_id, @Param("nam") String nam);
    @Query( value = "SELECT  * FROM xeploai where id = :id ", nativeQuery = true)
    XepLoai findXepLoaiById(@Param("id") Long id);

    @Query( value = "SELECT  * FROM xeploai where chibo_id = :chibo_id order by nam desc ", nativeQuery = true)
    List<XepLoai> findByChiBoId(@Param("chibo_id") Long chibo_id);
}
