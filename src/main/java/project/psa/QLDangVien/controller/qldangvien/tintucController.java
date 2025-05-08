package project.psa.QLDangVien.controller.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.tintucModel;
import project.psa.QLDangVien.service.qldangvien.tintucService;

@RestController
@RequestMapping(constant.API.PREFIX)
public class tintucController {
    @Autowired
    private tintucService tintucService;

    @PostMapping("/tintuc/create")
    @ResponseBody
    public ResponMessage createTintuc(@RequestBody tintucModel tintuc) {
        return tintucService.create(tintuc);
    }

    @PutMapping("/tintuc/update")
    @ResponseBody
    public ResponMessage updateTintuc(@RequestParam Long tintucId,@RequestBody tintucModel tintuc) {
        return tintucService.update(tintucId,tintuc);
    }
    @GetMapping("/tintuc/findAll")
    @ResponseBody
    public ResponMessage findAll() {
        return tintucService.findAll();
    }

    @GetMapping("/auth/tintuc/findApproved")
    @ResponseBody
    public ResponMessage findApproved() {
        return tintucService.findApproved();
    }

    @GetMapping("/auth/tintuc/findById")
    @ResponseBody
    public ResponMessage findById(@RequestParam Long tintucId) {
        return tintucService.findById(tintucId);
    }

    @DeleteMapping("/tintuc/delete")
    @ResponseBody
    public ResponMessage delete(@RequestParam Long tintucId) {
        return tintucService.delete(tintucId);
    }
}
