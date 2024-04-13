package com.poc.kafka.payload;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAnalyticsData {
    private int orderId;
    private float totalDistanceCovered;
    private float totalTimeTaken;
    private boolean deliveryOnTime;
    private String trafficSituation;
}
