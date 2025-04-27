package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.TrangThaiDangPhi;
import project.psa.QLDangVien.entity.qldangvien.TrangThaiDangPhiId;

@Repository
public interface TrangThaiDangPhiRepository extends JpaRepository<TrangThaiDangPhi, TrangThaiDangPhiId> {
}