package com.poc.kafka.consumer;

import com.poc.kafka.payload.DeliveryAnalyticsData;
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

    @KafkaListener(topics = "analytics", groupId = "randomGroupName")
    public void consumeDeliveryAnalyticsData(DeliveryAnalyticsData deliveryAnalyticsData) {
        LOGGER.info(String.format("Message consumed: %s", deliveryAnalyticsData));
        LOGGER.info("Delivery Successfully completed!! Here are some stats from your previous delivery...");
        LOGGER.info("Total distance covered by delivery partner: {}", deliveryAnalyticsData.getTotalDistanceCovered());
        LOGGER.info("Total time taken by delivery partner to reach at your door: {}", deliveryAnalyticsData.getTotalTimeTaken());
        if(deliveryAnalyticsData.isDeliveryOnTime()) {
            LOGGER.info("Delivery partner reached to you within time.. NO DELAY");
        } else {
            LOGGER.info("Traffic Situation at your area: {}", deliveryAnalyticsData.getTrafficSituation());
            LOGGER.info("delivery partner faced some DELAY due to high traffic at your area");
        }
    }
}
