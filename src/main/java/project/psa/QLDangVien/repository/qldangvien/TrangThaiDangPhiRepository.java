package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.TrangThaiDangPhi;
import project.psa.QLDangVien.entity.qldangvien.TrangThaiDangPhiId;
import project.psa.QLDangVien.model.qldangvien.trangthaidangphiDTO;

import java.util.List;

@Repository
public interface TrangThaiDangPhiRepository extends JpaRepository<TrangThaiDangPhi, TrangThaiDangPhiId> {
    @Query( value = "SELECT  * FROM trangthaidangphi where kydangphi_id = :kydangphi_id ", nativeQuery = true)
    List<TrangThaiDangPhi> findByKydangphiId(@Param("kydangphi_id") Long kydangphi_id);

    @Query( value = "SELECT  * FROM trangthaidangphi where kydangphi_id = :kydangphi_id and dangvien_id = :dangvien_id", nativeQuery = true)
    TrangThaiDangPhi findByKydangphiIdAndDangvienId(@Param("kydangphi_id") Long kydangphi_id,@Param("dangvien_id") Long dangvien_id);

    @Query(value = "SELECT t.dangvien_id, t.kydangphi_id, d.hoten, t.trangthai, t.nguoixacnhan, t.thoigianxacnhan " +
            "FROM trangthaidangphi t " +
            "JOIN dangvien d ON d.id = t.dangvien_id " +
            "WHERE t.kydangphi_id = :kydangphi_id",
            nativeQuery = true)
    List<Object[]> findTrangThaiDangPhiRawByKydangphiId(@Param("kydangphi_id") Long kydangphiId);

}