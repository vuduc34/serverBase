package project.psa.QLDangVien.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class accountDTO {
    private Long id;
    private String username;
    private Long status;
    private String tokenForgotPassword;
    private Instant timeCreatioToken;
    private String email;
    private String code;
    private String fullname;
    private String phoneNumber;
    private Long roleId;
    private String roleName;
}
