package com.example.kafka.service;

import java.util.logging.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final Logger logger = Logger.getLogger(this.getClass().getName());
  public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String topic, String message) {
    kafkaTemplate.send(topic, message);
    logger.info("Message sent to topic: " + topic + " with message: " + message);
  }

}
