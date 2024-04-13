package com.poc.kafka.producer;

import com.poc.kafka.payload.DeliveryAnalyticsData;
import com.poc.kafka.payload.DeliveryPartnerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class DominoAnalyticsProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DominoAnalyticsProducer.class);
    private final KafkaTemplate<String, DeliveryAnalyticsData> kafkaTemplate;

    public DominoAnalyticsProducer(KafkaTemplate<String, DeliveryAnalyticsData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishAnalyticsDataToCustomer(DeliveryAnalyticsData deliveryAnalyticsData) {
        Message<DeliveryAnalyticsData> message = MessageBuilder
                .withPayload(deliveryAnalyticsData)
                .setHeader(KafkaHeaders.TOPIC, "analytics")
                .build();

        kafkaTemplate.send(message);
        LOGGER.info("Analytics data Pubished to Customer: {}", deliveryAnalyticsData);
    }
}
