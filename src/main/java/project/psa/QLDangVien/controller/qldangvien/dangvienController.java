package project.psa.QLDangVien.controller.qldangvien;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.dangvienModel;
import project.psa.QLDangVien.service.qldangvien.dangvienService;

@RestController
@RequestMapping(constant.API.PREFIX+"/dangvien")
public class dangvienController {
    @Autowired
    private dangvienService dangvienService;
    @PostMapping("/create")
    @ResponseBody
    public ResponMessage create(@RequestBody dangvienModel data,@RequestParam Long chiboId) {
        return dangvienService.create(data,chiboId);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponMessage update(@RequestBody dangvienModel data,@RequestParam Long dangvienId) {
        return dangvienService.update(data,dangvienId);
    }

    @GetMapping("/findAll")
    @ResponseBody
    public ResponMessage findAll()  {
        return dangvienService.findAll();
    }

    @GetMapping("/findApproved")
    @ResponseBody
    public ResponMessage dangvienDaPheDuyet()  {
        return dangvienService.findDangvienDaPheDuyet();
    }

    @GetMapping("/findByChiBoId")
    @ResponseBody
    public ResponMessage findByChiBoId(@RequestParam Long chiboId)  {
        return dangvienService.findByChiboId(chiboId);
    }

    @Operation(summary = "Tìm kiếm đảng viên bằng từ khóa",
            description = "Tìm kiếm thông qua nội dung các trường như họ tên, mã thẻ đảng," +
                    "tên chi bộ, chức vụ chi bộ,...")
    @GetMapping("/findByKeyword")
    @ResponseBody
    public ResponMessage findByKeyword(@RequestParam String keyword)  {
        return dangvienService.findByKeyword(keyword);
    }
}
