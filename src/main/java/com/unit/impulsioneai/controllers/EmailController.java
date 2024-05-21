package com.unit.impulsioneai.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unit.impulsioneai.Services.EmailService;
import com.unit.impulsioneai.dtos.EmailRecordDto;
import com.unit.impulsioneai.models.EmailModel;

import java.util.Optional;
import java.util.UUID;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/email")
public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailRecordDto emailDto) {
    EmailModel emailModel = new EmailModel();
    BeanUtils.copyProperties(emailDto, emailModel);

    // Substituir quebras de linha por <br> tags no corpo do e-mail HTML
    String textWithLineBreaks = emailDto.text().replace("\n", "<br>");

    // Criar o corpo do e-mail HTML
    String body = "<html>"
                + "<body style='width:800px;'>"
                + "<h1>" + emailDto.subject() + "</h1>"
                + "<p style='max-width:800px; text-align: justify;'>" + textWithLineBreaks + "</p>"
                + "<br><img src='cid:footerImage'>"
                + "</body>"
                + "</html>";

    emailModel.setText(body);

    emailService.sendEmail(emailModel);
    return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
}

    // @GetMapping("/emails")
    // public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable) {
    //     return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
    // }

    @GetMapping("/emails/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value = "emailId") UUID emailId) {
        Optional<EmailModel> emailModelOptional = emailService.findById(emailId);
        if (!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
}
