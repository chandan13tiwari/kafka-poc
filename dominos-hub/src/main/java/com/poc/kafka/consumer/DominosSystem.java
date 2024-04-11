package com.poc.kafka.consumer;

import com.poc.kafka.payload.DeliveryPartnerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DominosSystem {

    private static final Logger LOGGER = LoggerFactory.getLogger(DominosSystem.class);

    private List<DeliveryPartnerData> sessionData = new ArrayList<>();


    @KafkaListener(topics = "domino", groupId = "randomGroupName")
    public void consumeDeliveryPartnerData(DeliveryPartnerData deliveryPartnerData) {
        LOGGER.info(String.format("Message consumed: %s", deliveryPartnerData));

        // collecting session data
        sessionData.add(deliveryPartnerData);

        if(deliveryPartnerData.getXCoordinate() == 0 && deliveryPartnerData.getYCoordinate() == 0) {
            LOGGER.info("Delivery Partner has delivered the order!!");
            LOGGER.info("Collecting the session data for Analytics...");

            // TODO Store the captured data in Domino's DB, so that they can perform analytics on that data later

        }
    }
}
