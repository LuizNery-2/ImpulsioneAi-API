package com.unit.impulsioneai.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unit.impulsioneai.enums.StatusEmail;
import com.unit.impulsioneai.models.EmailModel;
import com.unit.impulsioneai.repositories.EmailRepository;

import jakarta.mail.Part;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            jakarta.mail.internet.MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailModel.getEmailFrom());
            helper.setTo(emailModel.getEmailTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText(), true);

            // Adicionar a imagem de footer
            ClassPathResource footerImage = new ClassPathResource("static/images/Footer.png");
            helper.addInline("footerImage", footerImage);

            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException | jakarta.mail.MessagingException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
            e.printStackTrace();
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    // public Part<EmailModel> findAll(Pageable pageable) {
    //     return emailRepository.findAll(pageable);
    // }

    public Optional<EmailModel> findById(UUID emailId) {
        return emailRepository.findById(emailId);
    }

   

}
