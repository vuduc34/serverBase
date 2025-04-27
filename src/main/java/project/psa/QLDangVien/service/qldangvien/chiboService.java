package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.ChiBo;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.chiboModel;
import project.psa.QLDangVien.repository.qldangvien.ChiBoRepository;

@Service
public class chiboService {
    @Autowired
    private ChiBoRepository chiBoRepository;
    public ResponMessage create(chiboModel chibo) {
        ResponMessage responMessage = new ResponMessage();
        try {
            ChiBo chiBo = new ChiBo();
            chiBo.setBithu(chibo.getBithu());
            chiBo.setDanguycaptren(chibo.getDanguycaptren());
            chiBo.setDiachi(chibo.getDiachi());
            chiBo.setGhichu(chibo.getGhichu());
            chiBo.setNgaythanhlap(chibo.getNgaythanhlap());
            chiBo.setPhobithu(chibo.getPhobithu());
            chiBo.setTenchibo(chibo.getTenchibo());
            chiBo.setTrangthai(chibo.getTrangthai());
            chiBo = chiBoRepository.save(chiBo);
            responMessage.setData(chiBo);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
//            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(chiboModel chibo, Long chiboId) {
        ResponMessage responMessage = new ResponMessage();
        try {

            ChiBo chiBo = chiBoRepository.findChiBoById(chiboId);
            if(chiBo == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy chi bộ");
            } else {
                chiBo.setBithu(chibo.getBithu());
                chiBo.setDanguycaptren(chibo.getDanguycaptren());
                chiBo.setDiachi(chibo.getDiachi());
                chiBo.setGhichu(chibo.getGhichu());
                chiBo.setNgaythanhlap(chibo.getNgaythanhlap());
                chiBo.setPhobithu(chibo.getPhobithu());
                chiBo.setTenchibo(chibo.getTenchibo());
                chiBo.setTrangthai(chibo.getTrangthai());
                chiBo = chiBoRepository.save(chiBo);
                responMessage.setData(chiBo);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
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

            responMessage.setData(chiBoRepository.findAll());
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
//            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findChiboDangHoatDong() {
        ResponMessage responMessage = new ResponMessage();
        try {

            responMessage.setData(chiBoRepository.findChiBoHoatDong());
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
//            System.out.println(e.getMessage());
        }
        return responMessage;
    }


}
