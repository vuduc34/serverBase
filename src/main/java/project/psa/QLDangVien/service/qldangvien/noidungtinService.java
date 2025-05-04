package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.NoiDungTin;
import project.psa.QLDangVien.entity.qldangvien.TinTuc;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.noidungtinModel;
import project.psa.QLDangVien.model.qldangvien.tintucModel;
import project.psa.QLDangVien.repository.qldangvien.NoiDungTinRepository;
import project.psa.QLDangVien.repository.qldangvien.TinTucRepository;

import java.time.LocalDateTime;

@Service
public class noidungtinService {
    @Autowired
    private NoiDungTinRepository noiDungTinRepository;
    @Autowired
    private TinTucRepository tinTucRepository;

    public ResponMessage create(Long tintucId, noidungtinModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            TinTuc tinTuc = tinTucRepository.findTinTucById(tintucId);
            if(tinTuc == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy bài viết với id: "+tintucId);
            } else {
                NoiDungTin noiDungTin = new NoiDungTin();
                noiDungTin.setNoidung(model.getNoidung());
                noiDungTin.setThutu(model.getThutu());
                noiDungTin.setTintuc(tinTuc);
                noiDungTin.setUrl(model.getImageUrl());
                noiDungTin = noiDungTinRepository.save(noiDungTin);
                responMessage.setData(noiDungTin);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(Long noidungtinId, noidungtinModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            NoiDungTin noiDungTin = noiDungTinRepository.findNoiDungTinById(noidungtinId);
            if(noiDungTin == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin");
            } else {
                noiDungTin.setNoidung(model.getNoidung());
                noiDungTin.setThutu(model.getThutu());
                noiDungTin.setUrl(model.getImageUrl());
                noiDungTin = noiDungTinRepository.save(noiDungTin);
                responMessage.setData(noiDungTin);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage delete(Long noidungtinId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            noiDungTinRepository.deleteById(noidungtinId);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findByTintucId(Long tintucId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(noiDungTinRepository.findByTinTucId(tintucId));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }



}
