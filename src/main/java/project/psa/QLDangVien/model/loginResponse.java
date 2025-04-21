package project.psa.QLDangVien.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class loginResponse {
	private String token;
	private String username;
	private String role;
	private Long account_id;
	private String fullname;
	private Long status;
	private String email;
	private String phoneNumber;
}
