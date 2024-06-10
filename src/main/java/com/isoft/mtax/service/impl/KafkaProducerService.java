package com.isoft.mtax.service.impl;

import com.isoft.mtax.dto.EmailDetails;
import com.isoft.mtax.entity.TDSCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final String TOPIC = "email_topic";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(EmailDetails emailDetails) {
        kafkaTemplate.send(TOPIC, emailDetails);
    }
}
