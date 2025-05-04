package project.psa.QLDangVien.controller.qldangvien;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.service.qldangvien.kydangphiService;

@RestController
@RequestMapping(constant.API.PREFIX+"/kydangphi")
public class kydangphiController {
    @Autowired
    private kydangphiService kydangphiService;

    @PostMapping("/create")
    @ResponseBody
    public ResponMessage createKydangphi(@RequestParam Long sotien, String tenKydangphi) {
        return kydangphiService.create(sotien,tenKydangphi);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponMessage updateKydangphi(@RequestParam Long kydangphiId,@RequestParam String tenKydangphi,@RequestParam Long sotien) {
        return kydangphiService.update(kydangphiId,sotien,tenKydangphi);
    }
    @GetMapping("/findAll")
    @ResponseBody
    public ResponMessage findAll() {
        return kydangphiService.findAll();
    }
}
