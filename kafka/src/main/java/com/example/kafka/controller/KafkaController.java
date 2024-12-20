package com.example.kafka.controller;

import com.example.kafka.service.KafkaConsumerService;
import com.example.kafka.service.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
  private final KafkaProducerService kafkaProducerService;
  private final KafkaConsumerService kafkaConsumerService;
  public KafkaController(KafkaProducerService kafkaProducerService,
      KafkaConsumerService kafkaConsumerService) {
    this.kafkaProducerService = kafkaProducerService;
    this.kafkaConsumerService = kafkaConsumerService;
  }

  @GetMapping("/send")
  public String sendMessage(@RequestParam String topic, @RequestParam String message) {
    kafkaProducerService.sendMessage(topic, message);
    return  "Message sent to topic: " + topic;
  }

}
