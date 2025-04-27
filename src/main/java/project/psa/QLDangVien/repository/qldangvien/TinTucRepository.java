package project.psa.QLDangVien.repository.qldangvien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.qldangvien.TinTuc;

@Repository
public interface TinTucRepository extends JpaRepository<TinTuc, Long> {
    TinTuc findTinTucById(Long id);
    boolean existsById(Long id);
}