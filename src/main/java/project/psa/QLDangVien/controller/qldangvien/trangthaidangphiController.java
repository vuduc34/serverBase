package project.psa.QLDangVien.controller.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.tintucModel;
import project.psa.QLDangVien.service.qldangvien.trangthaidangphiService;

@RestController
@RequestMapping(constant.API.PREFIX+"/trangthaidangphi")
public class trangthaidangphiController {
    @Autowired
    private trangthaidangphiService trangthaidangphiService;

    @PostMapping("/confirm")
    @ResponseBody
    public ResponMessage confirm(@RequestParam Long kydangphiId,@RequestParam Long dangvienId) {
        return trangthaidangphiService.confirm(kydangphiId,dangvienId);
    }

    @GetMapping("/findByKydangphiId")
    @ResponseBody
    public ResponMessage findByKydangphiId(@RequestParam Long kydangphiId) {
        return trangthaidangphiService.findByKydangphiId(kydangphiId);
    }
}
