package project.psa.QLDangVien.controller.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.thedangModel;
import project.psa.QLDangVien.model.qldangvien.tintucModel;
import project.psa.QLDangVien.service.qldangvien.thedangService;

@RestController
@RequestMapping(constant.API.PREFIX+"/thedang")
public class thedangController {
    @Autowired
    private thedangService thedangService;
    @PostMapping("/create")
    @ResponseBody
    public ResponMessage createThedang(@RequestBody thedangModel model, @RequestParam Long dangvienId) {
        return thedangService.create(dangvienId,model);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponMessage updateThedang(@RequestBody thedangModel model, @RequestParam Long thedangId) {
        return thedangService.update(thedangId,model);
    }
    @GetMapping("/findDetailByDangvienId")
    @ResponseBody
    public ResponMessage findDetailByDangvienId(@RequestParam Long dangvienId) {
        return thedangService.findDetailByDangvienId(dangvienId);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public ResponMessage findAll() {
        return thedangService.findAll();
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponMessage delete(@RequestParam Long thedangId) {
        return thedangService.delete(thedangId);
    }
}
