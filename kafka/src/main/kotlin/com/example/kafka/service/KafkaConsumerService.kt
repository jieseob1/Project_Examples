package com.example.kafka.service

import com.example.kafka.dto.ProductEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory.getLogger

@Service
class KafkaConsumerService {
    private val log = getLogger(this::class.java)

    @KafkaListener(topics = ["att-product-manage"], groupId = "product-group")
    fun consume(event: ProductEvent?) {
        log.info("이벤트 받아따~ : {}", event)
    }

}