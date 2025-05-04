package project.psa.QLDangVien.controller.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.quyetdinhModel;
import project.psa.QLDangVien.service.qldangvien.quyetdinhService;

@RestController
@RequestMapping(constant.API.PREFIX+"/quyetdinh")
public class quyetdinhController {
    @Autowired
    private quyetdinhService quyetdinhService;

    @PostMapping("/create")
    @ResponseBody
    public ResponMessage createQuyetdinh(@RequestBody quyetdinhModel model, @RequestParam Long dangvienId) {
        return quyetdinhService.create(dangvienId,model);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponMessage updateQuyetdinh(@RequestParam Long quyedinhId,@RequestBody quyetdinhModel model) {
        return quyetdinhService.update(quyedinhId,model);
    }
    @GetMapping("/findByDangvienId")
    @ResponseBody
    public ResponMessage findByDangvienId(@RequestParam Long dangvienId) {
        return quyetdinhService.findByDangvienId(dangvienId);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponMessage delete(@RequestParam Long quyetdinhId) {
        return quyetdinhService.delete(quyetdinhId);
    }
}
