package project.psa.QLDangVien.service.qldangvien;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.psa.QLDangVien.common.constant;
import project.psa.QLDangVien.model.ResponMessage;
import project.psa.QLDangVien.model.qldangvien.ThongKeResponse;
import project.psa.QLDangVien.repository.auth.accountRepository;
import project.psa.QLDangVien.repository.qldangvien.ChiBoRepository;
import project.psa.QLDangVien.repository.qldangvien.DangVienRepository;
import project.psa.QLDangVien.repository.qldangvien.TinTucRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ThongKeService {
    private final DangVienRepository dangVienRepo;
    private final ChiBoRepository chiBoRepo;
    private final TinTucRepository tinTucRepo;
    private final accountRepository taiKhoanRepo;

    public ResponMessage thongKeTongQuan() {
        ResponMessage responMessage = new ResponMessage();
        try {
            Long tongSoDangVien = dangVienRepo.countDangVien();
            Long soNam = dangVienRepo.countNam();
            Long soNu = dangVienRepo.countNu();

            Long tongSoChiBo = chiBoRepo.countChiBo();

            Map<String, Long> dangVienTheoChiBo = new HashMap<>();
            for (Object[] row : dangVienRepo.countDangVienTheoChiBo()) {
                dangVienTheoChiBo.put((String) row[0], (Long) row[1]);
            }

            Long tongTinTuc = tinTucRepo.countAll();
            Long tinTucDaDuyet = tinTucRepo.countDaDuyet();

            Long tongTaiKhoan = taiKhoanRepo.countAll();
            Map<String, Long> taiKhoanTheoVaiTro = new HashMap<>();
            for (Object[] row : taiKhoanRepo.countTaiKhoanTheoVaiTro()) {
                taiKhoanTheoVaiTro.put((String) row[0], (Long) row[1]);
            }
            responMessage.setMessage(constant.MESSAGE.SUCCESS);
            responMessage.setResultCode(constant.RESULT_CODE.SUCCESS);
            responMessage.setData(new ThongKeResponse(
                    tongSoDangVien, soNam, soNu,
                    tongSoChiBo, dangVienTheoChiBo,
                    tongTinTuc, tinTucDaDuyet,
                    tongTaiKhoan, taiKhoanTheoVaiTro));

        } catch (Exception e) {
            responMessage.setMessage(e.getMessage());
            responMessage.setResultCode(constant.RESULT_CODE.ERROR);

        }
       return responMessage;
    }
}
