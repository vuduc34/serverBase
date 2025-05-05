package project.psa.QLDangVien.controller.qldangvien;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.ThongKeResponse;
import project.psa.QLDangVien.service.qldangvien.ThongKeService;

@RestController
@RequestMapping(constant.API.PREFIX+"/thongke")
@RequiredArgsConstructor
public class ThongKeController {

    private final ThongKeService thongKeService;

    @GetMapping
    @ResponseBody
    public ResponMessage getThongKe() {
        return thongKeService.thongKeTongQuan();
    }
}
