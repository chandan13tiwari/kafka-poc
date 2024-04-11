package com.poc.kafka.consumer;

import com.poc.kafka.payload.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);


    @KafkaListener(topics = "location", groupId = "myGroup")
    public void consumeJson(User user) {
        LOGGER.info(String.format("Message consumed: %s", user));
    }
}
