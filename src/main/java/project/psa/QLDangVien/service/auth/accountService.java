package project.psa.QLDangVien.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.config.jwt.jwtProvider;
import project.psa.QLDangVien.entity.auth.account;
import project.psa.QLDangVien.entity.auth.role;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.SignInData;
import project.psa.QLDangVien.model.loginResponse;
import project.psa.QLDangVien.model.signUpData;
import project.psa.QLDangVien.repository.auth.accountRepository;
import project.psa.QLDangVien.repository.auth.roleRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class accountService {
    private static final int EXPIRE_TOKEN = 10;
    @Autowired
    private accountRepository accountRepository;
    @Autowired
    private emailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private roleRepository roleRepository;
    @Autowired
    private jwtProvider jwtProvider;



    public String forgotPassword(String email) throws Exception {
        account account = accountRepository.findUserByEmail(email);
        if (account == null)
            return "not found email in system";
        String token = generateToken();
        while (accountRepository.existsByTokenForgotPassword(token)) {
            token = generateToken();
        }
        account.setTokenForgotPassword(token);
        account.setTimeCreatioToken(Instant.now());
        accountRepository.save(account);
//		System.out.println(account.getEmail());
        try {
            String message = emailService.sendEmail(token, account.getEmail());
            return message;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean resetPassword(String token, String password) {
        if (token == null)
            return false;
        if (password == null)
            return false;
        account account = accountRepository.findByTokenForgotPassword(token);
        if (account == null)
            return false;
        if (isexpire(account.getTimeCreatioToken()))
            return false;
        String newpasswordString = passwordEncoder.encode(password);
        account.setPassword(newpasswordString);
        accountRepository.save(account);
        return true;

    }

    private Boolean isexpire(Instant timeCreation) {
        Instant now = Instant.now();
        Duration diff = Duration.between(timeCreation, now);

        return diff.toMinutes() >= EXPIRE_TOKEN;

    }

    public String generateToken() {
        Random random = new Random();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            token.append(random.nextInt(10));
        }
        return token.toString();
    }

    public void sendMailActiveAccount(String code, String email) throws Exception {
        try {
             emailService.sendMailRegister(code, email);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponMessage changePassword(String username,String currentPw,String newPw) {
        ResponMessage responMessage = new ResponMessage();
        account account = accountRepository.findUserByUsername(username);
        if(account == null) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(constant.MESSAGE.NOT_FOUND_USER);
        } else if(!passwordEncoder.matches(currentPw, account.getPassword()))  {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(constant.MESSAGE.PASSWORD_INCORRECT);
        } else {
            account.setPassword(passwordEncoder.encode(newPw));
            accountRepository.save(account);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        }
        return responMessage;
    }

    public ResponMessage signIn(SignInData data) {

        account account = accountRepository.findUserByUsername(data.getUserName());
        ResponMessage responMessage = new ResponMessage();
//		logger.info(jwtProvider.getClientIp());
        if (account == null ) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(constant.MESSAGE.NOT_FOUND_USER);
            return responMessage;
        } else if (!passwordEncoder.matches(data.getPassWord(), account.getPassword())) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(constant.MESSAGE.PASSWORD_INCORRECT);
            return responMessage;

        } else if (!(account.getStatus() == constant.STATUS.ACTIVE)) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(constant.MESSAGE.ACCOUNT_DEACTIVE);
            return responMessage;
        } else {
            try {
                String token = jwtProvider.generateToken(data.getUserName());
                role roles = account.getRole();
                loginResponse loginResponse = new loginResponse();
                loginResponse.setToken(token);
                loginResponse.setUsername(data.getUserName());
                loginResponse.setRole(roles.getName());
                loginResponse.setAccount_id(account.getId());
                loginResponse.setFullname(account.getFullname());
                loginResponse.setEmail(account.getEmail());
                loginResponse.setStatus(account.getStatus());
                loginResponse.setPhoneNumber(account.getPhoneNumber());

                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
                responMessage.setData(loginResponse);
                return responMessage;
            } catch (Exception e) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.ERROR);
                responMessage.setData(e.getMessage());
                return responMessage;
            }
        }
    }

    public ResponMessage createAccount(signUpData signUp, String roleName) throws Exception {
        ResponMessage responMessage = new ResponMessage();
        try{
            role role = roleRepository.findByName(roleName);
            if (accountRepository.existsByUsername(signUp.getUserName())) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.USERNAME_EXIST);
                return responMessage;

            } else if (accountRepository.existsByEmail(signUp.getEmail())) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.EMAIL_EXIST);
                return responMessage;
            } else if(role == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.ROLE_ERROR);
                return responMessage;
            } else {
                account account = new account();
                account.setStatus(constant.STATUS.ACTIVE);
                account.setUsername(signUp.getUserName());
                account.setPassword(passwordEncoder.encode(signUp.getPassWord()));
                account.setPhoneNumber(signUp.getPhoneNumber());
                account.setFullname(signUp.getFullname());
                account.setRole(role);
                account.setEmail(signUp.getEmail());
                String token =generateToken();
                while (accountRepository.existsByCode(token)) {
                    token = generateToken();
                }
                account.setCode(token);
                accountRepository.save(account);
//                sendMailActiveAccount(token, signUp.getEmail());
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
                responMessage.setData(account);
                return responMessage;
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
            return  responMessage;
        }

    }

    public ResponMessage activeAccount(@RequestParam String code) {
        ResponMessage responMessage = new ResponMessage();
        account account = accountRepository.findByCode(code);
        if (account != null) {
            account.setStatus(constant.STATUS.ACTIVE);
            accountRepository.save(account);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } else {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(constant.MESSAGE.ERROR);
        }
        return responMessage;
    }

    public ResponMessage changeRole(String username,String role) {
        ResponMessage responMessage = new ResponMessage();
        try {
            account account = accountRepository.findUserByUsername(username);
            role roles = roleRepository.findByName(role);
            if(account == null || account.getStatus() == -1L) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.NOT_FOUND_USER);
            } else if(roles == null){
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.ROLE_ERROR);
            }  else{

                account.setRole(roles);
                accountRepository.save(account);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage activeStatus(String username) {
        ResponMessage responMessage = new ResponMessage();
        try {
            account account = accountRepository.findUserByUsername(username);
            if(account == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.NOT_FOUND_USER);
            } else {
                account.setStatus(constant.STATUS.ACTIVE);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
                responMessage.setData(accountRepository.save(account));
            }

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }
    public ResponMessage deactiveStatus(String username) {
        ResponMessage responMessage = new ResponMessage();
        try {
            account account = accountRepository.findUserByUsername(username);
            if(account == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.NOT_FOUND_USER);
            } else {
                account.setStatus(constant.STATUS.DE_ACTIVE);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
                responMessage.setData(accountRepository.save(account));
            }

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }


    public ResponMessage findAll() {
        ResponMessage responMessage = new ResponMessage();
        try {

            responMessage.setData(accountRepository.findAllAccount());
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findAllRole() {
        ResponMessage responMessage = new ResponMessage();
        try {

            List<role> roles = new ArrayList<>();
            roleRepository.findAll().forEach(e -> {
                e.setAccount(null);
                roles.add(e);
            });
            responMessage.setData(roles);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }
    public ResponMessage deleteAccount(String username) {
        ResponMessage responMessage = new ResponMessage();
        try {
            account account = accountRepository.findUserByUsername(username);
            account.setStatus(constant.STATUS.DELETED);
            accountRepository.save(account);
//            accountRepository.delete(account);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }





}
