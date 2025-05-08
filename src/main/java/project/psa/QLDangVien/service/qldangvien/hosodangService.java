package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.DangVien;
import project.psa.QLDangVien.entity.qldangvien.HosoDang;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.hosodangModel;
import project.psa.QLDangVien.repository.qldangvien.DangVienRepository;
import project.psa.QLDangVien.repository.qldangvien.HoSoDangRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class hosodangService {
    @Autowired
    private HoSoDangRepository hoSoDangRepository;
    @Autowired
    private DangVienRepository dangVienRepository;

    public ResponMessage create(Long dangvienId, hosodangModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            DangVien dangVien = dangVienRepository.findDangVienById(dangvienId);
            if(dangVien == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin đảng viên");
            } else {
                HosoDang hosoDang = new HosoDang();
                hosoDang.setTrangthai(constant.THONGTIN.SAVED);
                hosoDang.setDangvien(dangVien);
                hosoDang.setGhichu(model.getGhichu());
                hosoDang.setFileUrl(model.getFileUrl());
                hosoDang.setThoigiantao(LocalDateTime.now());
                hosoDang.setLoaihoso(model.getLoaihoso());
                hosoDang.setTaphoso(model.getTaphoso());
                hosoDang = hoSoDangRepository.save(hosoDang);
                responMessage.setData(hosoDang);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(Long hosodangId, hosodangModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            HosoDang hosoDang = hoSoDangRepository.findHosoDangById(hosodangId);
            if(hosoDang == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin hồ sơ");
            } else if(hosoDang.getTrangthai().equals(constant.THONGTIN.PENDING)) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không thể cập thật! Hồ sơ đang chờ phê duyệt");
            } else {
                hosoDang.setTrangthai(constant.THONGTIN.SAVED);
                hosoDang.setGhichu(model.getGhichu());
                hosoDang.setThoigiantao(LocalDateTime.now());
                hosoDang.setFileUrl(model.getFileUrl());
                hosoDang.setLoaihoso(model.getLoaihoso());
                hosoDang.setTaphoso(model.getTaphoso());
                hosoDang.setThoigianpheduyet(null);
                hosoDang.setNguoipheduyet(null);
                hosoDang = hoSoDangRepository.save(hosoDang);
                responMessage.setData(hosoDang);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findApprovedByDangvienId(Long dangvienId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(hoSoDangRepository.findHosoDangApprovedByDangvienId(dangvienId));
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
            responMessage.setData(hoSoDangRepository.findHosoDangByDangvienId(dangvienId));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findByListHosodangId(List<Long> listId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(hoSoDangRepository.findByIdIn(listId));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage delete(Long hosoId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            hoSoDangRepository.deleteById(hosoId);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }


}
