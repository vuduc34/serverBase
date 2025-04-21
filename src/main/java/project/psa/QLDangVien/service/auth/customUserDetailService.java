package project.psa.QLDangVien.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.entity.auth.account;
import project.psa.QLDangVien.repository.auth.accountRepository;


@Service
public class customUserDetailService implements UserDetailsService {
    @Autowired
    private accountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        account account = accountRepository.findUserByUsername(username);
        return customUserDetail.createCustomUserDetails(account);
    }
}
