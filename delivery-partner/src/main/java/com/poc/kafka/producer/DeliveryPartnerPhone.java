package com.poc.kafka.producer;

import com.poc.kafka.payload.DeliveryPartnerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class DeliveryPartnerPhone {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryPartnerPhone.class);

    private KafkaTemplate<String, DeliveryPartnerData> kafkaTemplate;

    public DeliveryPartnerPhone(KafkaTemplate<String, DeliveryPartnerData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishDeliveryPartnerData(DeliveryPartnerData deliveryPartnerData) throws InterruptedException {


        while(deliveryPartnerData.getXCoordinate() != 0 || deliveryPartnerData.getYCoordinate() != 0) {
            LOGGER.info("Delivery Partner Coordinates :: X-Coordinate: {}, Y-Coordinate: {}", deliveryPartnerData.getXCoordinate(), deliveryPartnerData.getYCoordinate());

            // adding some network delay
            TimeUnit.MILLISECONDS.sleep(500);

            if(deliveryPartnerData.getXCoordinate() == 0) {
                deliveryPartnerData.setYCoordinate(deliveryPartnerData.getYCoordinate() - 0.500);
            } else if(deliveryPartnerData.getYCoordinate() == 0) {
                deliveryPartnerData.setXCoordinate(deliveryPartnerData.getXCoordinate() - 0.500);
            } else {
                deliveryPartnerData.setXCoordinate(deliveryPartnerData.getXCoordinate() - 0.500);
                deliveryPartnerData.setYCoordinate(deliveryPartnerData.getYCoordinate() - 0.500);
            }

            // publishing delivery partner data to customer phone so that they can track their order
            publishMessageToCustomer(deliveryPartnerData);

            // publishing delivery partner data to Domino's hub to calculate the analytics
            publishMessageToDominos(deliveryPartnerData);

        }
    }

    private void publishMessageToCustomer(DeliveryPartnerData deliveryPartnerData) {
        Message<DeliveryPartnerData> message = MessageBuilder
                .withPayload(deliveryPartnerData)
                .setHeader(KafkaHeaders.TOPIC, "customer")
                .build();

        kafkaTemplate.send(message);
        LOGGER.info("Message Pubished to Customer: {}", deliveryPartnerData);
    }

    private void publishMessageToDominos(DeliveryPartnerData deliveryPartnerData) {
        Message<DeliveryPartnerData> message = MessageBuilder
                .withPayload(deliveryPartnerData)
                .setHeader(KafkaHeaders.TOPIC, "domino")
                .build();

        kafkaTemplate.send(message);
        LOGGER.info("Message Pubished to Domino's DB: {}", deliveryPartnerData);
    }
}
