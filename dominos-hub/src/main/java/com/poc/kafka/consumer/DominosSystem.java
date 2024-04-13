package com.poc.kafka.consumer;

import com.poc.kafka.entity.DominosTable;
import com.poc.kafka.payload.DeliveryAnalyticsData;
import com.poc.kafka.payload.DeliveryPartnerData;
import com.poc.kafka.producer.DominoAnalyticsProducer;
import com.poc.kafka.repository.DominosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DominosSystem {

    private static final Logger LOGGER = LoggerFactory.getLogger(DominosSystem.class);

    private static StringBuilder deliveryPartnerCollectiveData = new StringBuilder();
    private final DominosRepository dominosRepository;
    private final DominoAnalyticsProducer dominoAnalyticsProducer;

    public DominosSystem(DominosRepository dominosRepository, DominoAnalyticsProducer dominoAnalyticsProducer) {
        this.dominosRepository = dominosRepository;
        this.dominoAnalyticsProducer = dominoAnalyticsProducer;
    }


    @KafkaListener(topics = "domino", groupId = "randomGroupName")
    public void consumeDeliveryPartnerData(DeliveryPartnerData deliveryPartnerData) {
        LOGGER.info(String.format("Message consumed: %s", deliveryPartnerData));

        // collecting session data
        deliveryPartnerCollectiveData.append(deliveryPartnerData);

        if(deliveryPartnerData.getXCoordinate() == 0 && deliveryPartnerData.getYCoordinate() == 0) {
            LOGGER.info("Delivery Partner has delivered the order!!");
            LOGGER.info("Collecting the session data for Analytics...");

            // Store the captured data in Domino's DB, so that they can perform analytics on that data later
            DominosTable dominosTableData = DominosTable.builder()
                    .orderId(deliveryPartnerData.getOrderId())
                    .deliveryPartnerCollectiveData(deliveryPartnerCollectiveData.toString())
                    .build();

            dominosRepository.save(dominosTableData);

            // TODO Perform some analytics operations on that deliveryPartner collected data

            // Sending all the structured analytics data to customer at the end of delivery
            DeliveryAnalyticsData analyticsData = DeliveryAnalyticsData.builder()
                    .totalDistanceCovered(deliveryPartnerData.getDistance())
                    .totalTimeTaken(deliveryPartnerData.getEstimatedTime())
                    .deliveryOnTime(true)
                    .trafficSituation("Minimal traffic, delivery partner didn't stop for more than 2 mins during the delivery").build();
            dominoAnalyticsProducer.publishAnalyticsDataToCustomer(analyticsData);
            LOGGER.info("Analytics data published to customer's phone. Analytics Data: {}", analyticsData);
        }
    }
}
