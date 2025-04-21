package project.psa.QLDangVien.config.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.repository.auth.accountRepository;

import java.util.Date;

@Component
public class jwtProvider {
    @Autowired
    accountRepository accountRepository;
    private String jwtSecret ="VUVNDUC050120011234567890QWERTYUIPASDFGHJKLZXCVBNMQASXCFVGBJHBFHCDGVCGNVCGVHCFHBBCAOKFLWJDJGKDJRVJFHJFHDJRHFHDSHRYCHDJSJFHDH"; // Base64.getEncoder().encodeToString(key.getEncoded());
    public static final Long time_expire = 28800000L;

    public String generateToken(String userName) {
        Date date = new Date(System.currentTimeMillis() + jwtProvider.time_expire);
        String jwt = Jwts.builder().setSubject(userName).setExpiration(date).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return jwt;
    }

    public boolean validateToken(String token) {
        try {
            if(!(accountRepository.findUserByUsername(getLoginFormToke(token)).getStatus() == constant.STATUS.ACTIVE)) {
                return false;
            }
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginFormToke(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
