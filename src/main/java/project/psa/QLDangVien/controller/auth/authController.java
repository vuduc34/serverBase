package project.psa.QLDangVien.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.*;
import project.psa.QLDangVien.service.auth.accountService;


@RestController
@RequestMapping(constant.API.PREFIX_AUTH)
public class authController {

    @Autowired
    private accountService accountService;

    @PostMapping("/signin")
    @ResponseBody
    public ResponMessage signIn(@RequestBody SignInData data) {
        return accountService.signIn(data);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponMessage signup(@RequestBody signUpData dto) throws Exception {
        return  accountService.createAccount(dto,constant.ROLE.USER);

    }


    @PostMapping("/forgotpw")
    @ResponseBody
    public ResponMessage forgotPW(@RequestBody fotgotPW forgot) throws Exception {

        ResponMessage responMessage = new ResponMessage();
        try {
            String message = accountService.forgotPassword(forgot.getEmail());
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
            responMessage.setData(message);
            return responMessage;
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(constant.MESSAGE.ERROR);
            responMessage.setData(e.getMessage());
            return responMessage;
        }

    }

    @PostMapping("/resetpw")
    @ResponseBody
    public ResponMessage resetPW(@RequestBody resetPW reset) {
        ResponMessage responMessage = new ResponMessage();
        try {
            boolean message = accountService.resetPassword(reset.getToken(), reset.getPassword());
            if(message) {
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
                responMessage.setData(message);
            } else {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage(constant.MESSAGE.ERROR);
                responMessage.setData("Error occur");
            }

            return responMessage;
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(constant.MESSAGE.ERROR);
            responMessage.setData(e.getMessage());
            return responMessage;
        }
    }

//    @PostMapping("/active")
//    @ResponseBody
//    public ResponMessage activeAccount(@RequestParam String code) {
//        return accountService.activeAccount(code);
//    }

}
