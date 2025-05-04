package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.NoiDungTin;
import project.psa.QLDangVien.entity.qldangvien.TinTuc;

import java.util.List;

@Repository
public interface NoiDungTinRepository extends JpaRepository<NoiDungTin, Long> {
    NoiDungTin findNoiDungTinById(Long id);

    @Query( value = "SELECT  * FROM noidungtin where tintuc_id = :tintuc_id order by thutu asc ", nativeQuery = true)
    List<NoiDungTin> findByTinTucId(@Param("tintuc_id") Long tintuc_id);
}