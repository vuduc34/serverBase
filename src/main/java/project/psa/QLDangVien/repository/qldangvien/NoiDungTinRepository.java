package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.NoiDungTin;

@Repository
public interface NoiDungTinRepository extends JpaRepository<NoiDungTin, Long> {
}