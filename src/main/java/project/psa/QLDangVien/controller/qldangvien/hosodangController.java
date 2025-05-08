package project.psa.QLDangVien.controller.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.hosodangModel;
import project.psa.QLDangVien.service.qldangvien.hosodangService;

import java.util.List;

@RestController
@RequestMapping(constant.API.PREFIX+"/hoso")
public class hosodangController {
    @Autowired
    private hosodangService hosodangService;

    @PostMapping("/create")
    @ResponseBody
    public ResponMessage createHoso(@RequestBody hosodangModel model,@RequestParam Long dangvienId) {
        return hosodangService.create(dangvienId,model);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponMessage updateHoso(@RequestParam Long hosoId,@RequestBody hosodangModel model) {
        return hosodangService.update(hosoId,model);
    }
    @GetMapping("/findByDangvienId")
    @ResponseBody
    public ResponMessage findByDangvienId(@RequestParam Long dangvienId) {
        return hosodangService.findByDangvienId(dangvienId);
    }

    @GetMapping("/findApprovedByDangvienId")
    @ResponseBody
    public ResponMessage findApprovedByDangvienId(@RequestParam Long dangvienId) {
        return hosodangService.findApprovedByDangvienId(dangvienId);
    }

    @GetMapping("/findByListHosodangId")
    @ResponseBody
    public ResponMessage findByListHosodangId(@RequestParam List<Long> listId) {
        return hosodangService.findByListHosodangId(listId);
    }


    @DeleteMapping("/delete")
    @ResponseBody
    public ResponMessage delete(@RequestParam Long hosoid) {
        return hosodangService.delete(hosoid);
    }
}
