package project.psa.QLDangVien.controller.qldangvien;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.pheduyetModel;
import project.psa.QLDangVien.service.qldangvien.pheduyetService;

@RestController
@RequestMapping(constant.API.PREFIX+"/pheduyet")
@Tag(name = "pheduyet Controller", description = "Phê duyệt thông tin: thông tin đảng viên, hồ sơ đảng, tin tức")
public class pheduyetController {
    @Autowired
    private pheduyetService pheduyetService;


    @Operation(summary = "Gửi thông tin phê duyệt", description = "Dữ liệu đầu vào: Có 3 loại phê duyệt là: thongtin: phê duyệt thôn tin đảng viên, " +
            "tintuc: phê duyệt tin tức, hoso: phê duyệt hồ sơ đảng, phê duyệt loại nào thì điền id loại đó, các trường khác xóa hoặc bỏ trống." +
            "Ví dụ loại phê duyệt là thongtin thì chỉ điền dangvienId, còn tintucId và listHosoId để null hoặc xóa. Trường người phê duyệt sẽ là username của tài khoản.")
    @PostMapping("/create")
    @ResponseBody
    public ResponMessage create(@RequestBody pheduyetModel model) {
        return pheduyetService.create(model);
    }

    @GetMapping("/approval")
    @ResponseBody
    public ResponMessage approval(@RequestParam Long pheduyetId) {
        return pheduyetService.approval(pheduyetId);
    }

    @GetMapping("/reject")
    @ResponseBody
    public ResponMessage reject(@RequestParam Long pheduyetId) {
        return pheduyetService.reject(pheduyetId);
    }

    @GetMapping("/findByUsername")
    @ResponseBody
    public ResponMessage findByUsername(@RequestParam String username) {
        return pheduyetService.findPheduyetByUsername(username);
    }
}
