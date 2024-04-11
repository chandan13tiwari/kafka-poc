package com.poc.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.poc.kafka.config.Topic.*;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic locationCoordinates() {
        return TopicBuilder.name(TOPIC_LOCATION.name())
                .build();
    }

    @Bean
    public NewTopic timeTaken() {
        return TopicBuilder.name(TOPIC_TIME.name())
                .build();
    }

    @Bean
    public NewTopic distanceCovered() {
        return TopicBuilder.name(TOPIC_DISTANCE.name())
                .build();
    }
}
