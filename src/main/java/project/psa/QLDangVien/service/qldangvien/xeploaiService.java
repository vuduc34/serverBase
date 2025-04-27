package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.ChiBo;
import project.psa.QLDangVien.entity.qldangvien.XepLoai;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.repository.qldangvien.ChiBoRepository;
import project.psa.QLDangVien.repository.qldangvien.XepLoaiRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;

@Service
public class xeploaiService {
    @Autowired
    private XepLoaiRepository xepLoaiRepository;
    @Autowired
    private ChiBoRepository chiBoRepository;

    public ResponMessage create(Long chiboId, String nam, String xeploai) {
        ResponMessage responMessage = new ResponMessage();
        try {
            ChiBo chiBo = chiBoRepository.findChiBoById(chiboId);
           if(chiBo == null) {
               responMessage.setResultCode(constant.RESULT_CODE.ERROR);
               responMessage.setMessage("Không tìm thấy chi bộ");
           } else {
               XepLoai xepLoai = xepLoaiRepository.findByChiboIdAndNam(chiboId,nam);
               if(xepLoai == null) {
                   xepLoai = new XepLoai();
                   xepLoai.setChibo(chiBo);
                   xepLoai.setXeploai(xeploai);
                   xepLoai.setNam(nam);
                   xepLoai.setNguoitao(getCurrentUsername());
                   xepLoai.setThoigiantao(LocalDateTime.now());
                   xepLoai = xepLoaiRepository.save(xepLoai);
                   responMessage.setData(xepLoai);
                   responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                   responMessage.setMessage(constant.MESSAGE.SUCCESS);
               } else {
                   responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                   responMessage.setMessage("Chi bộ đã được xếp loại cho năm "+nam);
               }
           }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(Long xeploaiId,String xeploai) {
        ResponMessage responMessage = new ResponMessage();
        try {
           XepLoai xepLoai = xepLoaiRepository.findXepLoaiById(xeploaiId);
            if(xepLoai == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin xếp loại");
            } else {
                xepLoai.setXeploai(xeploai);
                xepLoai.setNguoitao(getCurrentUsername());
                xepLoai.setThoigiantao(LocalDateTime.now());
                xepLoai = xepLoaiRepository.save(xepLoai);
                responMessage.setData(xepLoai);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findByChiBoId(Long chiboId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(xepLoaiRepository.findByChiBoId(chiboId));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString(); // Nếu không phải UserDetails thì lấy toString (ví dụ String username)
            }
        }
        return null;
    }

}
