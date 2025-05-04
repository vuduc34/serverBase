package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.DangVien;
import project.psa.QLDangVien.entity.qldangvien.TheDang;
import project.psa.QLDangVien.entity.qldangvien.TinTuc;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.thedangModel;
import project.psa.QLDangVien.model.qldangvien.tintucModel;
import project.psa.QLDangVien.repository.qldangvien.DangVienRepository;
import project.psa.QLDangVien.repository.qldangvien.TheDangRepository;

import java.time.LocalDateTime;

@Service
public class thedangService {
    @Autowired
    private TheDangRepository theDangRepository;
    @Autowired
    private DangVienRepository dangVienRepository;

    public ResponMessage create(Long dangvienId,thedangModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            DangVien dangVien = dangVienRepository.findDangVienById(dangvienId);
            if(dangVien==null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin đảng viên");
            } else if(dangVien.getThedang() != null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Đảng viên đã được cấp thẻ đảng");
            } else if(!dangVien.getTrangthaidangvien().equals(constant.DANGVIEN.CHINHTHUC)) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Đảng viên phải là đảng viên chính thức mới được tạo thẻ đảng");
            } else {
                TheDang theDang = new TheDang();
                theDang.setDangvien(dangVien);
                theDang.setMathe(model.getMathe());
                theDang.setNgaycap(model.getNgaycap());
                theDang.setNoicapthe(model.getNoicapthe());
                theDang = theDangRepository.save(theDang);
                responMessage.setData(theDang);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(Long thedangId,thedangModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            TheDang theDang = theDangRepository.findTheDangById(thedangId);
            if(theDang==null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin thẻ đảng viên");
            } else {
                theDang.setMathe(model.getMathe());
                theDang.setNgaycap(model.getNgaycap());
                theDang.setNoicapthe(model.getNoicapthe());
                theDang = theDangRepository.save(theDang);
                responMessage.setData(theDang);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage delete(Long thedangId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            TheDang theDang = theDangRepository.findTheDangById(thedangId);
            if (theDang != null) {
                // Gỡ liên kết DangVien trước
                DangVien dangVien = theDang.getDangvien();
                if (dangVien != null) {
                    dangVien.setThedang(null);
                }
                theDangRepository.delete(theDang);
            }
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findDetailByDangvienId(Long dangvienId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(theDangRepository.findDetailByDangvienId(dangvienId));
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
            responMessage.setData(theDangRepository.findAllTheDang());
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }
}
