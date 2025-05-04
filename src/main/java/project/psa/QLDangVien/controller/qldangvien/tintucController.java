package project.psa.QLDangVien.controller.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.tintucModel;
import project.psa.QLDangVien.service.qldangvien.tintucService;

@RestController
@RequestMapping(constant.API.PREFIX+"/tintuc")
public class tintucController {
    @Autowired
    private tintucService tintucService;

    @PostMapping("/create")
    @ResponseBody
    public ResponMessage createTintuc(@RequestBody tintucModel tintuc) {
        return tintucService.create(tintuc);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponMessage updateTintuc(@RequestParam Long tintucId,@RequestBody tintucModel tintuc) {
        return tintucService.update(tintucId,tintuc);
    }
    @GetMapping("/findAll")
    @ResponseBody
    public ResponMessage findAll() {
        return tintucService.findAll();
    }

    @GetMapping("/findApproved")
    @ResponseBody
    public ResponMessage findApproved() {
        return tintucService.findApproved();
    }
    @DeleteMapping("/delete")
    @ResponseBody
    public ResponMessage delete(@RequestParam Long tintucId) {
        return tintucService.delete(tintucId);
    }
}
