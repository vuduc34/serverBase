package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.DangVien;
import project.psa.QLDangVien.entity.qldangvien.KyDangPhi;
import project.psa.QLDangVien.entity.qldangvien.TrangThaiDangPhi;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.repository.qldangvien.DangVienRepository;
import project.psa.QLDangVien.repository.qldangvien.KyDangPhiRepository;
import project.psa.QLDangVien.repository.qldangvien.TrangThaiDangPhiRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class kydangphiService {
    @Autowired
    private KyDangPhiRepository kyDangPhiRepository;
    @Autowired
    private DangVienRepository dangVienRepository;
    @Autowired
    private TrangThaiDangPhiRepository trangThaiDangPhiRepository;

    public ResponMessage create(Long sotien,String tenKyDangPhi) {
        ResponMessage responMessage = new ResponMessage();
        try {
            if(!kyDangPhiRepository.existsByTen(tenKyDangPhi)) {
                KyDangPhi kyDangPhi = new KyDangPhi();
                kyDangPhi.setTen(tenKyDangPhi);
                kyDangPhi.setSotien(sotien);
                kyDangPhi.setNguoitao(getCurrentUsername());
                kyDangPhi.setThoigiantao(LocalDateTime.now());
                kyDangPhi = kyDangPhiRepository.save(kyDangPhi);
                List<DangVien> dangVienList =dangVienRepository.findAll();
                for(int i = 0; i<dangVienList.size();i++) {
                    DangVien dangVien = dangVienList.get(i);
                    if(dangVien.getTrangthaidangvien().equals(constant.DANGVIEN.CHINHTHUC)
                            || dangVien.getTrangthaidangvien().equals(constant.DANGVIEN.DUBI))  {
                        TrangThaiDangPhi trangThaiDangPhi = new TrangThaiDangPhi();
                        trangThaiDangPhi.setKydangphi(kyDangPhi);
                        trangThaiDangPhi.setDangvien(dangVien);
                        trangThaiDangPhi.setTrangthai(constant.DANGPHI.CHUAHOANTHANH);
                        trangThaiDangPhiRepository.save(trangThaiDangPhi);
                    }

                }
                responMessage.setData(kyDangPhi);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            } else {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Tên kỳ đảng phí đã tồn tại!!");
            }

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(Long kydangphiId,Long sotien,String tenKyDangPhi) {
        ResponMessage responMessage = new ResponMessage();
        try {
            KyDangPhi kyDangPhi = kyDangPhiRepository.findKyDangPhiById(kydangphiId);
            if(kyDangPhi == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin");
            } else{
                List<TrangThaiDangPhi> trangThaiDangPhiList = trangThaiDangPhiRepository.findByKydangphiId(kydangphiId);
                boolean canUpdate  = true;
                for(int i =0; i<trangThaiDangPhiList.size();i++) {
                    if(trangThaiDangPhiList.get(i).getTrangthai().equals(constant.DANGPHI.HOANTHANH)) {
                        canUpdate = false;
                        break;
                    }
                }
                if(canUpdate) {
                    kyDangPhi.setSotien(sotien);
                    kyDangPhi.setTen(tenKyDangPhi);
                    kyDangPhi.setNguoitao(getCurrentUsername());
                    kyDangPhi.setThoigiantao(LocalDateTime.now());
                    kyDangPhi = kyDangPhiRepository.save(kyDangPhi);
                    responMessage.setData(kyDangPhi);
                    responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                    responMessage.setMessage(constant.MESSAGE.SUCCESS);
                } else {
                    responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                    responMessage.setMessage("Không thể cập nhật do đã có đảng viên đóng đảng phí kỳ này");
                }
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findAll() {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(kyDangPhiRepository.findAllKydangphi());
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
