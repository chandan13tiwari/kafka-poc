package com.poc.kafka.controller;

import com.poc.kafka.payload.User;
import com.poc.kafka.producer.KafkaJsonProducer;
import com.poc.kafka.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private KafkaProducer kafkaProducer;
    private KafkaJsonProducer kafkaJsonProducer;

    public MessageController(KafkaProducer kafkaProducer, KafkaJsonProducer kafkaJsonProducer) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaJsonProducer = kafkaJsonProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestParam("message") String message) {
        kafkaProducer.sendMessage(message);

        return ResponseEntity.ok("Message published to the topic");
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publishJson(@RequestBody User user) {
        kafkaJsonProducer.publishMessage(user);

        return ResponseEntity.ok("JSON Message published successfully!");
    }
}
