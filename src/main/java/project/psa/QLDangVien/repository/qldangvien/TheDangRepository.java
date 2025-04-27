package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.TheDang;

@Repository
public interface TheDangRepository extends JpaRepository<TheDang, Long> {
}
