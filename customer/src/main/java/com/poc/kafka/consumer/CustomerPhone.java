package com.poc.kafka.consumer;

import com.poc.kafka.payload.DeliveryPartnerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerPhone {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerPhone.class);


    @KafkaListener(topics = "customer", groupId = "randomGroupName")
    public void consumeDeliveryPartnerData(DeliveryPartnerData deliveryPartnerData) {
        LOGGER.info("Delivery Partner has picked up your Order!");
        LOGGER.info("Total distance: {}", deliveryPartnerData.getDistance());
        LOGGER.info("Estimated time to reach at your door: {}", deliveryPartnerData.getEstimatedTime());

        LOGGER.info(String.format("Message consumed: %s", deliveryPartnerData));

        if(deliveryPartnerData.getXCoordinate() == 0 && deliveryPartnerData.getYCoordinate() == 0) {
            LOGGER.info("Delivery Partner has reached at your location");
        }
    }
}
