package com.sekolah.websekolah.service;


import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;


    @Async
    public void forgotMail(String email, String subject, String newPassword) throws MessagingException {
        log.info("inside forgotMail");

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("sekolahku@Sekolah.sch.id");
            helper.setTo(email);
            helper.setSubject(subject);
            String htmlMsg = "<p><b>Your Login details for Web sekolah System</b><br><b>Email: </b> " + email + " <br><b>Password: </b> " + newPassword + "<br><a href=\"http://localhost:3000/login\">Click here to login</a></p>" + "Don't forget to change your password, thank you for using our service";
            mimeMessage.setContent(htmlMsg, "text/html");
        };

        try {
            javaMailSender.send(messagePreparator);
            log.info("Forgot password email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new IllegalStateException("Exception occurred when sending mail to " + email + ": " + e.getMessage());
        }
    }
}
