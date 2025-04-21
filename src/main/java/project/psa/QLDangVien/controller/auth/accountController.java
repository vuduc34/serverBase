package project.psa.QLDangVien.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.signUpData;
import project.psa.QLDangVien.service.auth.accountService;

@RestController
@RequestMapping(constant.API.PREFIX)
public class accountController {
    @Autowired
    private accountService accountService;

    @PostMapping("/account/create")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponMessage createAccount(@RequestBody signUpData signUpData, @RequestParam String roleName) throws Exception {
        return accountService.createAccount(signUpData,roleName);
    }

    @GetMapping("/account/changeRole")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponMessage changeRole(@RequestParam String username, @RequestParam String role) {
        return accountService.changeRole(username, role);
    }
    @GetMapping("/account/findAll")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponMessage findAlll() {
        return accountService.findAll();
    }

    @GetMapping("/account/findAllRole")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponMessage findAlllRole() {
        return accountService.findAllRole();
    }

    @GetMapping("/account/activeAccount")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponMessage activeAccount(@RequestParam String username) {
        return accountService.activeStatus(username);
    }

    @GetMapping("/account/deactiveAccount")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponMessage deactiveAccount(@RequestParam String username) {
        return accountService.deactiveStatus(username);
    }
    @DeleteMapping("/account/delete")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponMessage delete(@RequestParam String username) {
        return accountService.deleteAccount(username);
    }

}
