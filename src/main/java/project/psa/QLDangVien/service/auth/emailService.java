package project.psa.QLDangVien.service.auth;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class emailService
{
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String token, String email) throws Exception {
        try {
//            SimpleMailMessage msg = new SimpleMailMessage();
//            msg.setFrom("qldangvien.mail@gmail.com");
//            msg.setTo(email);
//            msg.setSubject("Xác nhận yêu cầu đặt lại mật khẩu");
//            msg.setText("Kính gửi Quý khách,\n\n"
//                    + "Chúng tôi đã nhận được yêu cầu thiết lập lại mật khẩu cho tài khoản của Quý khách.\n"
//                    + "Vui lòng sử dụng mã xác thực dưới đây để tiến hành đặt lại mật khẩu:\n\n"
//                    + "Mã xác thực: " + token + "\n\n"
//                    + "⚠️ Lưu ý: Để đảm bảo an toàn cho tài khoản, xin vui lòng không chia sẻ mã này với bất kỳ ai. "
//                    + "Việc tiết lộ mã có thể dẫn đến mất quyền truy cập vào tài khoản.\n\n"
//                    + "Nếu Quý khách không thực hiện yêu cầu này, vui lòng bỏ qua email này.\n\n"
//                    + "Trân trọng cảm ơn Quý khách đã sử dụng dịch vụ của chúng tôi!\n\n"
//                    + "Trân trọng,\n"
//                    + "Đỗ Đại Dương\n"
//                    + "Số điện thoại: 0123 456 789");
//            mailSender.send(msg);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("qldangvien.mail@gmail.com");
            helper.setTo(email);
            helper.setSubject("Yêu cầu khôi phục mật khẩu");

            String htmlContent = "<div style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333;\">"
                    + "<p>Kính gửi Quý khách,</p>"
                    + "<p>Chúng tôi đã nhận được yêu cầu thiết lập lại mật khẩu cho tài khoản của Quý khách.</p>"
                    + "<p>Vui lòng sử dụng mã xác thực dưới đây để tiến hành đặt lại mật khẩu:</p>"
                    + "<h2 style=\"color: #007BFF;\">Mã xác thực: " + token + "</h2>"
                    + "<p style=\"color: red;\"><strong>⚠️ Lưu ý:</strong> Vui lòng không chia sẻ mã này với bất kỳ ai. Việc tiết lộ mã có thể dẫn đến mất quyền truy cập vào tài khoản.</p>"
                    + "<p>Nếu Quý khách không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>"
                    + "<p>Trân trọng cảm ơn Quý khách đã sử dụng dịch vụ của chúng tôi!</p>"
                    + "<br>"
                    + "<p>Trân trọng,</p>"
                    + "<p><strong>Đỗ Đại Dương</strong><br>Số điện thoại: 0123 456 789</p>"
                    + "</div>";


            helper.setText(htmlContent, true);

            mailSender.send(message);
            return "send mail successfully";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String sendMailRegister(String code, String email) throws Exception {
        try {
//            SimpleMailMessage msg = new SimpleMailMessage();
//            msg.setFrom("qldangvien.mail@gmail.com");
//            msg.setTo(email);
//            msg.setSubject("Email kích hoạt tài khoản");
//            msg.setText("Thân gửi bạn!\n"
//                    + "Hệ thống đã nhận được yêu cầu đăng kí tài khoản của bạn. Đây là mã code để kích hoạt tài khoản. Vui lòng không tiết lộ mã code với bất kỳ ai, nếu tiết lộ, bạn có nguy cơ bị mất tài khoản.  \n"
//                    + "Code : " + code + "\n" + "Nếu không phải bạn, vui lòng bỏ qua email này.\n \n \n"
//                    + "Trân trọng cảm ơn!\n" + "Đỗ Đại Dương.\n" + "SĐT: 0123456789");
//            mailSender.send(msg);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("qldangvien.mail@gmail.com");
            helper.setTo(email);
            helper.setSubject("Kích hoạt tài khoản của bạn");
            String htmlContent = "<div style=\"font-family: Arial, sans-serif; font-size: 14px; color: #333;\">"
                    + "<p>Kính gửi Quý khách,</p>"
                    + "<p>Chúng tôi đã nhận được yêu cầu kích hoạt tài khoản của Quý khách.</p>"
                    + "<p>Vui lòng sử dụng mã xác thực dưới đây để tiến hành kích hoạt tài khoản:</p>"
                    + "<h2 style=\"color: #007BFF;\">Mã xác thực: " + code + "</h2>"
                    + "<p style=\"color: red;\"><strong>⚠️ Lưu ý:</strong> Vui lòng không chia sẻ mã này với bất kỳ ai. Việc tiết lộ mã có thể dẫn đến mất quyền truy cập vào tài khoản.</p>"
                    + "<p>Nếu Quý khách không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>"
                    + "<p>Trân trọng cảm ơn Quý khách đã sử dụng dịch vụ của chúng tôi!</p>"
                    + "<br>"
                    + "<p>Trân trọng,</p>"
                    + "<p><strong>Đỗ Đại Dương</strong><br>Số điện thoại: 0123 456 789</p>"
                    + "</div>";


            helper.setText(htmlContent, true);

            mailSender.send(message);



            helper.setText(htmlContent, true);

            mailSender.send(message);
            return "send mail successfully";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}