package com.isoft.mtax.service.impl;

import com.isoft.mtax.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class KafkaCounsumerService {
    @Autowired
    private JavaMailSender mailSender;

    @KafkaListener(topics = "email_topic",groupId = "group_id")
    public void consume(EmailDetails emailDetails){
        try{
            sendEmail(emailDetails);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }


    }


    public void sendEmail(EmailDetails emailDetails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDetails.getTo());
        message.setSubject(emailDetails.getSubject());
        message.setText(emailDetails.getBody());
        message.setFrom("eranjanipathak.it@gmail.com");

        mailSender.send(message);
    }
}
