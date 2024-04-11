package com.poc.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic customer() {
        return TopicBuilder.name("customer")
                .build();
    }

    @Bean
    public NewTopic domino() {
        return TopicBuilder.name("domino")
                .build();
    }

}
