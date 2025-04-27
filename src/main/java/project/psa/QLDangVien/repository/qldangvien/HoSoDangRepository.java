package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.HosoDang;

@Repository
public interface HoSoDangRepository extends JpaRepository<HosoDang, Long> {
    boolean existsById(Long id);
    HosoDang findHosoDangById(Long id);
}
