package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.ChiBo;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.chiboModel;
import project.psa.QLDangVien.model.qldangvien.updateChiboModel;
import project.psa.QLDangVien.repository.qldangvien.ChiBoRepository;

@Service
public class chiboService {
    @Autowired
    private ChiBoRepository chiBoRepository;
    public ResponMessage create(chiboModel chibo) {
        ResponMessage responMessage = new ResponMessage();
        try {
            if(chibo.getLoai().equals(constant.LOAICHIBO.DANGUY)) {
                ChiBo chiBo = new ChiBo();
                chiBo.setBithu(chibo.getBithu());
                chiBo.setDanguycaptren(chibo.getDanguycaptren());
                chiBo.setDiachi(chibo.getDiachi());
                chiBo.setGhichu(chibo.getGhichu());
                chiBo.setNgaythanhlap(chibo.getNgaythanhlap());
                chiBo.setPhobithu(chibo.getPhobithu());
                chiBo.setTenchibo(chibo.getTenchibo());
                chiBo.setTrangthai(chibo.getTrangthai());
                chiBo.setLoai(constant.LOAICHIBO.DANGUY);
                chiBo = chiBoRepository.save(chiBo);
                responMessage.setData(chiBo);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            } else if(chibo.getLoai().equals(constant.LOAICHIBO.DANGBO)) {
                ChiBo captren = chiBoRepository.findChiBoById(chibo.getDanguyCaptrenId());
                if(captren == null) {
                    responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                    responMessage.setMessage("Không tìm thấy thông tin đảng ủy cấp trên");
                } else if(!captren.getLoai().equals(constant.LOAICHIBO.DANGUY)) {
                    responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                    responMessage.setMessage("Đảng bộ phải trực thuộc đảng ủy cấp trên");
                } else {
                    ChiBo chiBo = new ChiBo();
                    chiBo.setBithu(chibo.getBithu());
                    chiBo.setDanguycaptren(chibo.getDanguycaptren());
                    chiBo.setDiachi(chibo.getDiachi());
                    chiBo.setGhichu(chibo.getGhichu());
                    chiBo.setNgaythanhlap(chibo.getNgaythanhlap());
                    chiBo.setPhobithu(chibo.getPhobithu());
                    chiBo.setTenchibo(chibo.getTenchibo());
                    chiBo.setTrangthai(chibo.getTrangthai());
                    chiBo.setLoai(constant.LOAICHIBO.DANGBO);
                    chiBo.setDanguyCaptrenId(captren.getId());
                    chiBo = chiBoRepository.save(chiBo);
                    responMessage.setData(chiBo);
                    responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                    responMessage.setMessage(constant.MESSAGE.SUCCESS);
                }
            } else {
                ChiBo captren = chiBoRepository.findChiBoById(chibo.getDanguyCaptrenId());
                if(captren == null) {
                    responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                    responMessage.setMessage("Không tìm thấy thông tin đảng bộ cấp trên");
                } else if(!captren.getLoai().equals(constant.LOAICHIBO.DANGBO)) {
                    responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                    responMessage.setMessage("Đảng bộ phải trực thuộc đảng bộ cấp trên");
                } else {
                    ChiBo chiBo = new ChiBo();
                    chiBo.setBithu(chibo.getBithu());
                    chiBo.setDanguycaptren(chibo.getDanguycaptren());
                    chiBo.setDiachi(chibo.getDiachi());
                    chiBo.setGhichu(chibo.getGhichu());
                    chiBo.setNgaythanhlap(chibo.getNgaythanhlap());
                    chiBo.setPhobithu(chibo.getPhobithu());
                    chiBo.setTenchibo(chibo.getTenchibo());
                    chiBo.setTrangthai(chibo.getTrangthai());
                    chiBo.setLoai(constant.LOAICHIBO.CHIBO);
                    chiBo.setDanguyCaptrenId(captren.getId());
                    chiBo = chiBoRepository.save(chiBo);
                    responMessage.setData(chiBo);
                    responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                    responMessage.setMessage(constant.MESSAGE.SUCCESS);
                }
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
//            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(updateChiboModel chibo, Long chiboId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            ChiBo chiBo = chiBoRepository.findChiBoById(chiboId);
            if(chiBo == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin");
            } else {
                if(chiBo.getLoai().equals(constant.LOAICHIBO.DANGUY)) {
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
                } else if(chiBo.getLoai().equals(constant.LOAICHIBO.DANGBO)) {
                    ChiBo captren = chiBoRepository.findChiBoById(chibo.getDanguyCaptrenId());
                    if(captren == null) {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Không tìm thấy thông tin đảng ủy cấp trên");
                    } else if(!captren.getLoai().equals(constant.LOAICHIBO.DANGUY)) {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Đảng bộ phải trực thuộc đảng ủy cấp trên");
                    } else {
                        chiBo.setBithu(chibo.getBithu());
                        chiBo.setDanguycaptren(chibo.getDanguycaptren());
                        chiBo.setDiachi(chibo.getDiachi());
                        chiBo.setGhichu(chibo.getGhichu());
                        chiBo.setNgaythanhlap(chibo.getNgaythanhlap());
                        chiBo.setPhobithu(chibo.getPhobithu());
                        chiBo.setTenchibo(chibo.getTenchibo());
                        chiBo.setTrangthai(chibo.getTrangthai());
                        chiBo.setDanguyCaptrenId(captren.getId());
                        chiBo = chiBoRepository.save(chiBo);
                        responMessage.setData(chiBo);
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage(constant.MESSAGE.SUCCESS);
                    }
                } else {
                    ChiBo captren = chiBoRepository.findChiBoById(chibo.getDanguyCaptrenId());
                    if(captren == null) {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Không tìm thấy thông tin đảng bộ cấp trên");
                    } else if(!captren.getLoai().equals(constant.LOAICHIBO.DANGBO)) {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Đảng bộ phải trực thuộc đảng bộ cấp trên");
                    } else {
                        chiBo.setBithu(chibo.getBithu());
                        chiBo.setDanguycaptren(chibo.getDanguycaptren());
                        chiBo.setDiachi(chibo.getDiachi());
                        chiBo.setGhichu(chibo.getGhichu());
                        chiBo.setNgaythanhlap(chibo.getNgaythanhlap());
                        chiBo.setPhobithu(chibo.getPhobithu());
                        chiBo.setTenchibo(chibo.getTenchibo());
                        chiBo.setTrangthai(chibo.getTrangthai());
                        chiBo.setDanguyCaptrenId(captren.getId());
                        chiBo = chiBoRepository.save(chiBo);
                        responMessage.setData(chiBo);
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage(constant.MESSAGE.SUCCESS);
                    }
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


    public ResponMessage findAllDangUy() {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(chiBoRepository.findChiBoByLoai(constant.LOAICHIBO.DANGUY));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findAllChibo() {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(chiBoRepository.findChiBoByLoai(constant.LOAICHIBO.CHIBO));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findAllDangbo() {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(chiBoRepository.findChiBoByLoai(constant.LOAICHIBO.DANGBO));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findDangBoByDanguyCaptrenId(Long danguyCaptrenId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(chiBoRepository.findDangBoByDanguyCaptrenId(danguyCaptrenId));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }


    public ResponMessage findChiboByDangboCaptrenId(Long dangboCaptrenId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(chiBoRepository.findChiboByDangBoCaptrenId(dangboCaptrenId));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }


}
