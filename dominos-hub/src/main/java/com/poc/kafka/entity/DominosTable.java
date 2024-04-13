package com.poc.kafka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dominos")
public class DominosTable implements Serializable {
    @Id
    @Column(name = "orderId", nullable = false)
    private int orderId;

    @Lob
    @Column(name = "collectiveData")
    private String deliveryPartnerCollectiveData;
}
