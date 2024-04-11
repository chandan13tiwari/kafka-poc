package com.poc.kafka.config;

public enum Topic {
    TOPIC_LOCATION("location"),
    TOPIC_TIME("time"),
    TOPIC_DISTANCE("distance");

    private String topic;

    Topic(String topic) {
        this.topic = topic;
    }
}
