package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.TrangThaiDangPhi;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.trangthaidangphiDTO;
import project.psa.QLDangVien.repository.qldangvien.TrangThaiDangPhiRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class trangthaidangphiService {
    @Autowired
    private TrangThaiDangPhiRepository trangThaiDangPhiRepository;

    public ResponMessage confirm(Long kydangphiId,Long dangvienId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            TrangThaiDangPhi trangThaiDangPhi = trangThaiDangPhiRepository.findByKydangphiIdAndDangvienId(kydangphiId,dangvienId);
            if(trangThaiDangPhi == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin");
            } else {
                trangThaiDangPhi.setTrangthai(constant.DANGPHI.HOANTHANH);
                trangThaiDangPhi.setNguoixacnhan(getCurrentUsername());
                trangThaiDangPhi.setThoigianxacnhan(LocalDateTime.now());
                trangThaiDangPhi = trangThaiDangPhiRepository.save(trangThaiDangPhi);
                responMessage.setData(trangThaiDangPhi);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }
    public ResponMessage findByKydangphiId(Long kydangphiId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            List<Object[]> rawDataList = trangThaiDangPhiRepository.findTrangThaiDangPhiRawByKydangphiId(kydangphiId);
            List<trangthaidangphiDTO> resultList = new ArrayList<>();

            for (Object[] row : rawDataList) {
                trangthaidangphiDTO dto = new trangthaidangphiDTO();
                dto.setDangvienId(row[0] != null ? row[0].toString() : null);
                dto.setKydangphiId(row[1] != null ? row[1].toString() : null);
                dto.setHotenDangvien(row[2] != null ? row[2].toString() : null);
                dto.setTrangthai(row[3] != null ? row[3].toString() : null);
                dto.setNguoixacnhan(row[4] != null ? row[4].toString() : null);
                if (row[5] != null) {
                    Timestamp timestamp = (Timestamp) row[5];
                    dto.setThoigianxacnhan(timestamp.toLocalDateTime());
                } else {
                    dto.setThoigianxacnhan(null);
                }
                dto.setTenkydangphi(row[6] != null ? row[6].toString() : null);
                dto.setSotien(row[7] != null ? row[7].toString() : null);
                resultList.add(dto);
            }
            responMessage.setData(resultList);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findByDangvienId(Long dangvienId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            List<Object[]> rawDataList = trangThaiDangPhiRepository.findTrangThaiDangPhiRawByDangvienId(dangvienId);
            List<trangthaidangphiDTO> resultList = new ArrayList<>();

            for (Object[] row : rawDataList) {
                trangthaidangphiDTO dto = new trangthaidangphiDTO();
                dto.setDangvienId(row[0] != null ? row[0].toString() : null);
                dto.setKydangphiId(row[1] != null ? row[1].toString() : null);
                dto.setHotenDangvien(row[2] != null ? row[2].toString() : null);
                dto.setTrangthai(row[3] != null ? row[3].toString() : null);
                dto.setNguoixacnhan(row[4] != null ? row[4].toString() : null);
                if (row[5] != null) {
                    Timestamp timestamp = (Timestamp) row[5];
                    dto.setThoigianxacnhan(timestamp.toLocalDateTime());
                } else {
                    dto.setThoigianxacnhan(null);
                }

                dto.setTenkydangphi(row[6] != null ? row[6].toString() : null);
                dto.setSotien(row[7] != null ? row[7].toString() : null);
                resultList.add(dto);
            }
            responMessage.setData(resultList);
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
