package project.psa.QLDangVien.controller.qldangvien;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.chiboModel;
import project.psa.QLDangVien.model.qldangvien.updateChiboModel;
import project.psa.QLDangVien.model.signUpData;
import project.psa.QLDangVien.service.qldangvien.chiboService;

@RestController
@RequestMapping(constant.API.PREFIX+"/chibo")
public class chiboController {
    @Autowired
    private chiboService chiboService;


    @Operation(summary = "Tạo thông tin đảng ủy, đảng bộ, chi bộ", description = "Nếu loại là danguy ( đảng ủy ) thì không cần thông tin" +
            " danguyCaptrenId, nếu là dangbo thì danguyCaptrenId phải là Id của đảng ủy, chibo thì danguyCaptrenId phải là id của đảng bộ")
    @PostMapping("/create")
    @ResponseBody
    public ResponMessage createChibo(@RequestBody chiboModel data) {
        return chiboService.create(data);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponMessage updateChibo(@RequestBody updateChiboModel data, @RequestParam Long chiboId) {
        return chiboService.update(data,chiboId);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public ResponMessage findAll()  {
        return chiboService.findAll();
    }

    @Operation(summary = "Gọi API này đầu tiên để lấy danh sách đảng ủy")
    @GetMapping("/findAllDanguy")
    @ResponseBody
    public ResponMessage findAllDanguy()  {
        return chiboService.findAllDangUy();
    }

    @GetMapping("/findAllDangbo")
    @ResponseBody
    public ResponMessage findAllDangbo()  {
        return chiboService.findAllDangbo();
    }

    @GetMapping("/findAllChibo")
    @ResponseBody
    public ResponMessage findAllChibo()  {
        return chiboService.findAllChibo();
    }

    @GetMapping("/findChiboByDangboCaptrenId")
    @ResponseBody
    public ResponMessage findChiboByDangboCaptrenId(@RequestParam Long dangboCaptrenId)  {
        return chiboService.findChiboByDangboCaptrenId(dangboCaptrenId);
    }

    @GetMapping("/findDangboByDangUyCaptrenId")
    @ResponseBody
    public ResponMessage findDangboByDangUyCaptrenId(@RequestParam Long danguyCaptrenId)  {
        return chiboService.findDangBoByDanguyCaptrenId(danguyCaptrenId);
    }

    @GetMapping("/chiBoDangHoatDong")
    @ResponseBody
    public ResponMessage chiBoDangHoatDong()  {
        return chiboService.findChiboDangHoatDong();
    }
}
