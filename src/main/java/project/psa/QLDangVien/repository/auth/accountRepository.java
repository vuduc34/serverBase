package project.psa.QLDangVien.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.psa.QLDangVien.entity.auth.account;

import java.util.List;

@Repository
public interface accountRepository extends JpaRepository<account, Long> {
    account findUserByUsername(String username);
    account findUserByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    account findByTokenForgotPassword(String token);

    Boolean existsByTokenForgotPassword(String token);

    Boolean existsByCode(String code);

    account findByCode(String code);

    @Query( value = "SELECT  * FROM account where status != -1 ", nativeQuery = true)
    List<account> findAllAccount();
    @Query( value = "SELECT  * FROM account where id = :id ", nativeQuery = true)
    account findAccountById(@Param("id") Long id);

    @Query("SELECT COUNT(t) FROM account t WHERE t.status = 1  or t.status = 0")
    Long countAll();

    @Query("SELECT r.name, COUNT(a.id) FROM account a JOIN a.role r WHERE a.status = 1  or a.status = 0 GROUP BY r.name")
    List<Object[]> countTaiKhoanTheoVaiTro();
}
