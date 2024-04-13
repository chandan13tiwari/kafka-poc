package com.poc.kafka;

import com.poc.kafka.payload.DeliveryPartnerData;
import com.poc.kafka.producer.DeliveryPartnerPhone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DeliveryPartner
{
    private final DeliveryPartnerPhone deliveryPartnerPhoneProducer;

    public DeliveryPartner(DeliveryPartnerPhone deliveryPartnerPhoneProducer) {
        this.deliveryPartnerPhoneProducer = deliveryPartnerPhoneProducer;
    }

    public static void main( String[] args ) throws InterruptedException {
        ApplicationContext context = SpringApplication.run(DeliveryPartner.class, args);
        DeliveryPartnerPhone producer = context.getBean(DeliveryPartnerPhone.class);

        // business logic to calculate shortest distance and total time taken to delivery
        DeliveryPartnerData deliveryBoy1 = DeliveryPartnerData.builder()
                .orderId(1)
                .firstname("Guddu")
                .lastname("Yadav")
                .distance(1500)
                .estimatedTime(5)
                .xCoordinate((int) ((Math.random() * (50 - 10)) + 10))
                .yCoordinate((int) ((Math.random() * (50 - 10)) + 10))
                .build();

        producer.publishDeliveryPartnerData(deliveryBoy1);
    }

}
