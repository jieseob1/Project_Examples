package com.example.kafka.controller

import com.example.kafka.dto.ProductEvent
import com.example.kafka.service.KafkaProducerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController(
    private val kafkaProducerService: KafkaProducerService
) {

    @PostMapping("/send")
    fun sendMessage(event: ProductEvent): String {
        kafkaProducerService.sendMessage(event)
        return "send Message Complete"
    }
}