package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.qldangvien.ChiBo;
import project.psa.QLDangVien.entity.qldangvien.DangVien;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.dangvienDTO;
import project.psa.QLDangVien.model.qldangvien.dangvienModel;
import project.psa.QLDangVien.repository.qldangvien.ChiBoRepository;
import project.psa.QLDangVien.repository.qldangvien.DangVienRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class dangvienService {
    @Autowired
    private DangVienRepository dangVienRepository;
    @Autowired
    private ChiBoRepository chiBoRepository;

    public ResponMessage create(dangvienModel model, Long chiboId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            ChiBo chiBo = chiBoRepository.findChiBoById(chiboId);
            if(chiBo == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy chi bộ");
            } else {
                DangVien dangVien = new DangVien();
                dangVien.setChibo(chiBo);
                dangVien.setChucdanh(model.getChucdanh());
                dangVien.setChucvuchibo(model.getChucvuchibo());
                dangVien.setChucvuchinhquyen(model.getChucvuchinhquyen());
                dangVien.setChucvudanguy(model.getChucvudanguy());
                dangVien.setDantoc(model.getDantoc());
                dangVien.setChucvudoanthe(model.getChucvudoanthe());
                dangVien.setChuyennmon(model.getChuyennmon());
                dangVien.setGioitinh(model.getGioitinh());
                dangVien.setHoten(model.getHoten());
                dangVien.setNgaychinhthuc(model.getNgaychinhthuc());
                dangVien.setNgaysinh(model.getNgaysinh());
                dangVien.setNgayvaodang(model.getNgayvaodang());
                dangVien.setNoihiennay(model.getNoihiennay());
                dangVien.setQuequan(model.getQuequan());
                dangVien.setNguoigioithieu1(model.getNguoigioithieu1());
                dangVien.setNguoigioithieu2(model.getNguoigioithieu2());
                dangVien.setTrangthaidangvien(model.getTrangthaidangvien());
                dangVien.setNoisinhhoatdang(model.getNoisinhhoatdang());
                dangVien.setTrinhdochinhtri(model.getTrinhdochinhtri());
                dangVien.setTrinhdongoaingu(model.getTrinhdongoaingu());
                dangVien.setTrinhdovanhoa(model.getTrinhdovanhoa());
                dangVien.setTrangthaithongtin(constant.THONGTIN.SAVED);
                dangVien = dangVienRepository.save(dangVien);
                responMessage.setData(dangVien);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage update(dangvienModel model, Long dangvienId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            DangVien dangVien = dangVienRepository.findDangVienById(dangvienId);
            if(dangVien == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy đảng viên");
            } else if(dangVien.getTrangthaithongtin().equals(constant.THONGTIN.PENDING)) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không thể cập nhật! Thông tin đang chờ phê duyệt");
            } else {
                dangVien.setChucdanh(model.getChucdanh());
                dangVien.setChucvuchibo(model.getChucvuchibo());
                dangVien.setChucvuchinhquyen(model.getChucvuchinhquyen());
                dangVien.setChucvudanguy(model.getChucvudanguy());
                dangVien.setDantoc(model.getDantoc());
                dangVien.setChucvudoanthe(model.getChucvudoanthe());
                dangVien.setChuyennmon(model.getChuyennmon());
                dangVien.setGioitinh(model.getGioitinh());
                dangVien.setHoten(model.getHoten());
                dangVien.setNgaychinhthuc(model.getNgaychinhthuc());
                dangVien.setNgaysinh(model.getNgaysinh());
                dangVien.setNgayvaodang(model.getNgayvaodang());
                dangVien.setNoihiennay(model.getNoihiennay());
                dangVien.setQuequan(model.getQuequan());
                dangVien.setNguoigioithieu1(model.getNguoigioithieu1());
                dangVien.setNguoigioithieu2(model.getNguoigioithieu2());
                dangVien.setTrangthaidangvien(model.getTrangthaidangvien());
                dangVien.setNoisinhhoatdang(model.getNoisinhhoatdang());
                dangVien.setTrinhdochinhtri(model.getTrinhdochinhtri());
                dangVien.setTrinhdongoaingu(model.getTrinhdongoaingu());
                dangVien.setTrinhdovanhoa(model.getTrinhdovanhoa());
                dangVien.setTrangthaithongtin(constant.THONGTIN.SAVED);
                dangVien.setThoigianpheduyet(null);
                dangVien.setNguoipheduyet(null);
                dangVien = dangVienRepository.save(dangVien);

                responMessage.setData(dangVien);
                responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                responMessage.setMessage(constant.MESSAGE.SUCCESS);
            }
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findAll() {
        ResponMessage responMessage = new ResponMessage();
        try {
            List<DangVien> dangVienList  = dangVienRepository.findAll();
            responMessage.setData(dangVienList);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findDangvienDaPheDuyet() {
        ResponMessage responMessage = new ResponMessage();
        try {
            List<DangVien> dangVienList  = dangVienRepository.findDangViensApproved();
            responMessage.setData(dangVienList);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findByChiboId(Long chiboId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            List<DangVien> dangVienList  = dangVienRepository.findDangVienByChiBoId(chiboId);
            responMessage.setData(dangVienList);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findByDangvienId(Long dangvienId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(dangVienRepository.findDangVienById(dangvienId));
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findByKeyword(String text) {
        ResponMessage responMessage = new ResponMessage();
        try {
//            List<Object[]> results = dangVienRepository.findDangVienByText(text);
//            List<dangvienDTO> dtos = results.stream()
//                    .map(row -> new dangvienDTO(
//                            ((Number) row[0]).longValue(),  // id
//                            (String) row[1],                // hoten
//                            (String) row[2],                // tenchibo
//                            (String) row[3]                 // mathe
//                    ))
//                    .collect(Collectors.toList());
            List<DangVien> result = dangVienRepository.findDangVienByText(text);
            responMessage.setData(result);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
        return responMessage;
    }

}
