package com.example.kafka.service;

import java.util.logging.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  @KafkaListener(topics = "my_topic", groupId = "my_group_id")
  public void consume(String message) {
  logger.info("Consumed message: " + message);
  }
}
