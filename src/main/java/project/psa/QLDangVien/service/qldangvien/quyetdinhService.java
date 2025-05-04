package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.DangVien;
import project.psa.QLDangVien.entity.qldangvien.QuyetDinh;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.quyetdinhModel;
import project.psa.QLDangVien.repository.qldangvien.DangVienRepository;
import project.psa.QLDangVien.repository.qldangvien.QuyetDinhRepository;

@Service
public class quyetdinhService {
    @Autowired
    private QuyetDinhRepository quyetDinhRepository;
    @Autowired
    private DangVienRepository dangVienRepository;

    public ResponMessage create(Long dangvienId, quyetdinhModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            DangVien dangVien = dangVienRepository.findDangVienById(dangvienId);
            if(dangVien == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin đảng viên");
            } else {
                QuyetDinh quyetDinh = new QuyetDinh();
                quyetDinh.setDangvien(dangVien);
                quyetDinh.setFileUrl(model.getFileUrl());
                quyetDinh.setLoaiquyetdinh(model.getLoaiquyetdinh());
                quyetDinh.setTenquyetdinh(model.getTenquyetdinh());
                quyetDinh.setNam(model.getNam());
                quyetDinh = quyetDinhRepository.save(quyetDinh);
                responMessage.setData(quyetDinh);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(Long quyedinhId, quyetdinhModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            QuyetDinh quyetDinh = quyetDinhRepository.findQuyetDinhById(quyedinhId);
            if(quyetDinh == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin");
            } else {
                quyetDinh.setFileUrl(model.getFileUrl());
                quyetDinh.setLoaiquyetdinh(model.getLoaiquyetdinh());
                quyetDinh.setTenquyetdinh(model.getTenquyetdinh());
                quyetDinh.setNam(model.getNam());
                quyetDinh = quyetDinhRepository.save(quyetDinh);
                responMessage.setData(quyetDinh);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage delete(Long quyetdinhId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            quyetDinhRepository.deleteById(quyetdinhId);
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
            responMessage.setData(quyetDinhRepository.findByDangvienId(dangvienId));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }


}
