package com.poc.kafka.payload;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartnerData {
    private int orderId;
    private String firstname;
    private String lastname;
    private double xCoordinate;
    private double yCoordinate;
    private float distance;
    private float estimatedTime;
}
