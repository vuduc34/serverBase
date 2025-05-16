package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.TinTuc;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.tintucModel;
import project.psa.QLDangVien.repository.qldangvien.TinTucRepository;
import java.time.LocalDateTime;

@Service
public class tintucService {
    @Autowired
    private TinTucRepository tinTucRepository;

    public ResponMessage create(tintucModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            TinTuc tinTuc = new TinTuc();
            tinTuc.setNguoitao(getCurrentUsername());
            tinTuc.setTrangthai(constant.THONGTIN.SAVED);
            tinTuc.setThoigiantao(LocalDateTime.now());
            tinTuc.setNoidungtin(model.getNoidungtin());
            tinTuc.setTieude(model.getTieude());
            tinTuc.setUrl(model.getUrl());
            tinTuc = tinTucRepository.save(tinTuc);
            responMessage.setData(tinTuc);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(Long tintucId,tintucModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            TinTuc tinTuc = tinTucRepository.findTinTucById(tintucId);
            if(tinTuc == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin cần cập nhật");
            } else if(tinTuc.getTrangthai().equals(constant.THONGTIN.PENDING)) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không thể cập nhật! Thông tin đang chờ phê duyệt");
            } else {
                tinTuc.setNguoitao(getCurrentUsername());
                tinTuc.setTrangthai(constant.THONGTIN.SAVED);
                tinTuc.setThoigiantao(LocalDateTime.now());
                tinTuc.setThoigianpheduyet(null);
                tinTuc.setNguoipheduyet(null);
                tinTuc.setNoidungtin(model.getNoidungtin());
                tinTuc.setTieude(model.getTieude());
                tinTuc.setUrl(model.getUrl());
                tinTuc = tinTucRepository.save(tinTuc);
                responMessage.setData(tinTuc);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage delete(Long tintucId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            tinTucRepository.deleteById(tintucId);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findAll() {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(tinTucRepository.findAllTintuc());
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findById(Long id) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(tinTucRepository.findTinTucById(id));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findApproved() {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(tinTucRepository.findApproved());
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
