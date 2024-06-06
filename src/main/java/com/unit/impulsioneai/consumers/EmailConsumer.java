package com.unit.impulsioneai.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.unit.impulsioneai.Services.EmailService;
import com.unit.impulsioneai.dtos.EmailRecordDto;
import com.unit.impulsioneai.models.EmailModel;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    /*@RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailRecordDto emailDto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
    }*/
}
