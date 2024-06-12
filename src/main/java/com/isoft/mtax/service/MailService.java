package com.isoft.mtax.service;

import com.isoft.mtax.dto.EmailDetails;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.service.impl.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    KafkaProducerService kafkaProducerService;

    public <T extends Customer> void sendEmailNotification(T customer) {

        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setTo(customer.getEmail()); // Email recipient

        String text = "We are delighted to welcome "+customer.getCustomerName()+" to Maa Mundeswari Tax Consultancy \n\n" +
                "Thank you for choosing us as your trusted partner for tax consultancy services.";

        emailDetails.setSubject("Notification for "+customer.getClass().getSimpleName());
        emailDetails.setBody(text);
        kafkaProducerService.sendMessage(emailDetails);

    }
}
