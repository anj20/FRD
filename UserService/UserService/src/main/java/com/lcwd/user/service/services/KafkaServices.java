package com.lcwd.user.service.services;

import com.lcwd.user.service.config.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServices {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(KafkaServices.class);

    public void sendMessage(String message) {

        this.kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME, message);
        logger.info(message);
    }

}