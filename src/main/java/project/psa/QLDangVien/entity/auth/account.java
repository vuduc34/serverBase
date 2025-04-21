package project.psa.QLDangVien.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
@Entity
@Getter
@Setter
public class account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column
    private Long status;
    @Column
    private String tokenForgotPassword;
    @Column
    private Instant timeCreatioToken;
    @Column
    private String email;
    @Column
    private String code;
    @Column
    private String fullname;
    @Column
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnore
    private role role;


}
