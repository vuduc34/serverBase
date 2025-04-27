package project.psa.QLDangVien.controller.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.chiboModel;
import project.psa.QLDangVien.service.qldangvien.xeploaiService;

@RestController
@RequestMapping(constant.API.PREFIX+"/xeploai")
public class xeploaiController {
    @Autowired
    private xeploaiService xeploaiService;

    @PostMapping("/create")
    @ResponseBody
    public ResponMessage createXeploai(@RequestParam Long chiboId, String nam, String xeploai) {
        return xeploaiService.create(chiboId,nam,xeploai);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponMessage updateXeploai(@RequestParam Long xeploaiId,@RequestParam String xeploai) {
        return xeploaiService.update(xeploaiId,xeploai);
    }
    @GetMapping("/findByChiBoId")
    @ResponseBody
    public ResponMessage findByChiBoId(@RequestParam Long chiboId) {
        return xeploaiService.findByChiBoId(chiboId);
    }
}
