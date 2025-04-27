package project.psa.QLDangVien.service.qldangvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.entity.auth.account;
import project.psa.QLDangVien.entity.qldangvien.DangVien;
import project.psa.QLDangVien.entity.qldangvien.HosoDang;
import project.psa.QLDangVien.entity.qldangvien.PheDuyet;
import project.psa.QLDangVien.entity.qldangvien.TinTuc;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.pheduyetModel;
import project.psa.QLDangVien.repository.auth.accountRepository;
import project.psa.QLDangVien.repository.qldangvien.DangVienRepository;
import project.psa.QLDangVien.repository.qldangvien.HoSoDangRepository;
import project.psa.QLDangVien.repository.qldangvien.PheDuyetRepository;
import project.psa.QLDangVien.repository.qldangvien.TinTucRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class pheduyetService {
    @Autowired
    private accountRepository accountRepository;
    @Autowired
    private PheDuyetRepository pheDuyetRepository;
    @Autowired
    private DangVienRepository dangVienRepository;
    @Autowired
    private TinTucRepository tinTucRepository;
    @Autowired
    private HoSoDangRepository hoSoDangRepository;

    public ResponMessage create(pheduyetModel model) {
        ResponMessage responMessage = new ResponMessage();
        try {
            account account = accountRepository.findUserByUsername(model.getNguoipheduyet());
            if(account == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy người phê duyệt");
            } else {
                // Phê duyệt thông tin đảng viên
                if(model.getLoaipheduyet().equals(constant.LOAIPHEDUYET.THONGTINDANGVIEN)) {
                    DangVien dangVien = dangVienRepository.findDangVienById(model.getDangvienId());
                    if(dangVien!=null && dangVien.getTrangthaithongtin().equals(constant.THONGTIN.SAVED)) {
                        PheDuyet pheDuyet = new PheDuyet();
                        pheDuyet.setLoaipheduyet(constant.LOAIPHEDUYET.THONGTINDANGVIEN);
                        pheDuyet.setNguoipheduyet(model.getNguoipheduyet());
                        pheDuyet.setDangvienId(model.getDangvienId());
                        pheDuyet.setTrangthai(constant.THONGTIN.PENDING);
                        pheDuyet.setThoigianguipheduyet(LocalDateTime.now());
                        pheDuyet.setGhichu(model.getGhichu());
                        pheDuyetRepository.save(pheDuyet);
                        dangVien.setTrangthaithongtin(constant.THONGTIN.PENDING);
                        dangVien.setNguoipheduyet(model.getNguoipheduyet());
                        dangVienRepository.save(dangVien);
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage(constant.MESSAGE.SUCCESS);
                    } else {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Gửi phê duyệt thông tin đảng viên không thành công");
                    }
                } //Phê duyệt tin tức
                else if(model.getLoaipheduyet().equals(constant.LOAIPHEDUYET.TINTUC)) {
                    TinTuc tinTuc = tinTucRepository.findTinTucById(model.getTintucId());
                    if(tinTuc!=null && tinTuc.getTrangthai().equals(constant.THONGTIN.SAVED)) {
                        PheDuyet pheDuyet = new PheDuyet();
                        pheDuyet.setLoaipheduyet(constant.LOAIPHEDUYET.TINTUC);
                        pheDuyet.setNguoipheduyet(model.getNguoipheduyet());
                        pheDuyet.setTintucId(model.getTintucId());
                        pheDuyet.setTrangthai(constant.THONGTIN.PENDING);
                        pheDuyet.setThoigianguipheduyet(LocalDateTime.now());
                        pheDuyet.setGhichu(model.getGhichu());
                        pheDuyetRepository.save(pheDuyet);
                        tinTuc.setTrangthai(constant.THONGTIN.PENDING);
                        tinTuc.setNguoipheduyet(model.getNguoipheduyet());
                        tinTucRepository.save(tinTuc);
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage(constant.MESSAGE.SUCCESS);
                    } else {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Gửi phê duyệt tin tức không thành công");
                    }
                } // Phê duyệt hồ sơ đảng
                else if(model.getLoaipheduyet().equals(constant.LOAIPHEDUYET.HOSODANG)) {
                    List<Long>  listId = model.getListHosoId();
                    boolean hasInvalidId = false;
                    for(int i = 0; i<listId.size(); i++) {
                        if(!hoSoDangRepository.existsById(listId.get(i))) {
                            hasInvalidId = true;
                            break;
                        }
                    }
                    if(hasInvalidId){
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Gửi phê duyệt hồ sơ không thành công");
                    } else {
                        PheDuyet pheDuyet = new PheDuyet();
                        pheDuyet.setLoaipheduyet(constant.LOAIPHEDUYET.HOSODANG);
                        pheDuyet.setNguoipheduyet(model.getNguoipheduyet());
                        pheDuyet.setListHosoId(model.getListHosoId());
                        pheDuyet.setTrangthai(constant.THONGTIN.PENDING);
                        pheDuyet.setThoigianguipheduyet(LocalDateTime.now());
                        pheDuyet.setGhichu(model.getGhichu());
                        pheDuyetRepository.save(pheDuyet);
                        for(int i=0; i<listId.size();i++) {
                            HosoDang hosoDang = hoSoDangRepository.findHosoDangById(listId.get(i));
                            hosoDang.setNguoipheduyet(model.getNguoipheduyet());
                            hosoDang.setTrangthai(constant.THONGTIN.PENDING);
                            hoSoDangRepository.save(hosoDang);
                        }
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage("Gửi phê duyệt hồ sơ thành công");
                    }
                } else {
                    responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                    responMessage.setMessage("Loại hồ sơ không thuộc loại cần phê duyệt");
                }
            }

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage approval(Long pheduyetId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            PheDuyet pheDuyet = pheDuyetRepository.findPheDuyetById(pheduyetId);
            if(pheDuyet == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin phê duyệt");
            }else if(!(pheDuyet.getTrangthai().equals(constant.THONGTIN.PENDING))) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Nội dung đã được phê duyệt");
            } else {
                // Phê duyệt thông tin đảng viên
                if(pheDuyet.getLoaipheduyet().equals(constant.LOAIPHEDUYET.THONGTINDANGVIEN)) {
                    DangVien dangVien = dangVienRepository.findDangVienById(pheDuyet.getDangvienId());
                    if(dangVien!=null) {
                        pheDuyet.setTrangthai(constant.THONGTIN.APPROVED);
                        pheDuyetRepository.save(pheDuyet);
                        dangVien.setTrangthaithongtin(constant.THONGTIN.APPROVED);
                        dangVien.setThoigianpheduyet(LocalDateTime.now());
                        dangVienRepository.save(dangVien);
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage(constant.MESSAGE.SUCCESS);
                    } else {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Phê duyệt thông tin đảng viên không thành công");
                    }
                } //Phê duyệt tin tức
                else if(pheDuyet.getLoaipheduyet().equals(constant.LOAIPHEDUYET.TINTUC)) {
                    TinTuc tinTuc = tinTucRepository.findTinTucById(pheDuyet.getTintucId());
                    if(tinTuc!=null) {
                        pheDuyet.setTrangthai(constant.THONGTIN.APPROVED);
                        pheDuyetRepository.save(pheDuyet);
                        tinTuc.setTrangthai(constant.THONGTIN.APPROVED);
                        tinTuc.setThoigianpheduyet(LocalDateTime.now());
                        tinTucRepository.save(tinTuc);
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage(constant.MESSAGE.SUCCESS);
                    } else {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Gửi phê duyệt tin tức không thành công");
                    }
                } // Phê duyệt hồ sơ đảng
                else if(pheDuyet.getLoaipheduyet().equals(constant.LOAIPHEDUYET.HOSODANG)) {
                    List<Long>  listId = pheDuyet.getListHosoId();
                    boolean hasInvalidId = false;
                    for(int i = 0; i<listId.size(); i++) {
                        if(!hoSoDangRepository.existsById(listId.get(i))) {
                            hasInvalidId = true;
                            break;
                        }
                    }
                    if(hasInvalidId){
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Phê duyệt hồ sơ không thành công");
                    } else {
                        pheDuyet.setTrangthai(constant.THONGTIN.APPROVED);
                        pheDuyetRepository.save(pheDuyet);
                        for(int i=0; i<listId.size();i++) {
                            HosoDang hosoDang = hoSoDangRepository.findHosoDangById(listId.get(i));
                            hosoDang.setTrangthai(constant.THONGTIN.APPROVED);
                            hosoDang.setThoigianpheduyet(LocalDateTime.now());
                            hoSoDangRepository.save(hosoDang);
                        }
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage("Phê duyệt hồ sơ thành công");
                    }
                }
            }

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage reject(Long pheduyetId) {
        ResponMessage responMessage = new ResponMessage();
        try {
            PheDuyet pheDuyet = pheDuyetRepository.findPheDuyetById(pheduyetId);
            if(pheDuyet == null) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Không tìm thấy thông tin phê duyệt");
            } else if(!(pheDuyet.getTrangthai().equals(constant.THONGTIN.PENDING))) {
                responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                responMessage.setMessage("Nội dung đã được phê duyệt");
            } else {
                // Phê duyệt thông tin đảng viên
                if(pheDuyet.getLoaipheduyet().equals(constant.LOAIPHEDUYET.THONGTINDANGVIEN)) {
                    DangVien dangVien = dangVienRepository.findDangVienById(pheDuyet.getDangvienId());
                    if(dangVien!=null) {
                        pheDuyet.setTrangthai(constant.THONGTIN.REJECT);
                        pheDuyetRepository.save(pheDuyet);
                        dangVien.setTrangthaithongtin(constant.THONGTIN.REJECT);
                        dangVien.setThoigianpheduyet(LocalDateTime.now());
                        dangVienRepository.save(dangVien);
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage(constant.MESSAGE.SUCCESS);
                    } else {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Phê duyệt thông tin đảng viên không thành công");
                    }
                } //Phê duyệt tin tức
                else if(pheDuyet.getLoaipheduyet().equals(constant.LOAIPHEDUYET.TINTUC)) {
                    TinTuc tinTuc = tinTucRepository.findTinTucById(pheDuyet.getTintucId());
                    if(tinTuc!=null) {
                        pheDuyet.setTrangthai(constant.THONGTIN.REJECT);
                        pheDuyetRepository.save(pheDuyet);
                        tinTuc.setTrangthai(constant.THONGTIN.REJECT);
                        tinTuc.setThoigianpheduyet(LocalDateTime.now());
                        tinTucRepository.save(tinTuc);
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage(constant.MESSAGE.SUCCESS);
                    } else {
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Gửi phê duyệt tin tức không thành công");
                    }
                } // Phê duyệt hồ sơ đảng
                else if(pheDuyet.getLoaipheduyet().equals(constant.LOAIPHEDUYET.HOSODANG)) {
                    List<Long>  listId = pheDuyet.getListHosoId();
                    boolean hasInvalidId = false;
                    for(int i = 0; i<listId.size(); i++) {
                        if(!hoSoDangRepository.existsById(listId.get(i))) {
                            hasInvalidId = true;
                            break;
                        }
                    }
                    if(hasInvalidId){
                        responMessage.setResultCode(constant.RESULT_CODE.ERROR);
                        responMessage.setMessage("Phê duyệt hồ sơ không thành công");
                    } else {
                        pheDuyet.setTrangthai(constant.THONGTIN.REJECT);
                        pheDuyetRepository.save(pheDuyet);
                        for(int i=0; i<listId.size();i++) {
                            HosoDang hosoDang = hoSoDangRepository.findHosoDangById(listId.get(i));
                            hosoDang.setTrangthai(constant.THONGTIN.REJECT);
                            hosoDang.setThoigianpheduyet(LocalDateTime.now());
                            hoSoDangRepository.save(hosoDang);
                        }
                        responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
                        responMessage.setMessage("Phê duyệt hồ sơ thành công");
                    }
                }
            }

        } catch (Exception e) {
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);
            responMessage.setMessage(e.getMessage());
        }
        return responMessage;
    }

    public ResponMessage findPheduyetByUsername(String username) {
        ResponMessage responMessage = new ResponMessage();
        try {
            responMessage.setData(pheDuyetRepository.findByUsername(username));
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
