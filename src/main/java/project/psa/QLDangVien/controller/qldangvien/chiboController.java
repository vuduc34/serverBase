package project.psa.QLDangVien.controller.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.chiboModel;
import project.psa.QLDangVien.model.signUpData;
import project.psa.QLDangVien.service.qldangvien.chiboService;

@RestController
@RequestMapping(constant.API.PREFIX+"/chibo")
public class chiboController {
    @Autowired
    private chiboService chiboService;

    @PostMapping("/create")
    @ResponseBody
    public ResponMessage createChibo(@RequestBody chiboModel data) {
        return chiboService.create(data);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponMessage updateChibo(@RequestBody chiboModel data,@RequestParam Long chiboId) {
        return chiboService.update(data,chiboId);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public ResponMessage findAll()  {
        return chiboService.findAll();
    }
    @GetMapping("/chiBoDangHoatDong")
    @ResponseBody
    public ResponMessage chiBoDangHoatDong()  {
        return chiboService.findChiboDangHoatDong();
    }
}
