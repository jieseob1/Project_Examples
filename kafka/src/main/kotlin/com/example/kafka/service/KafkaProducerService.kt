package com.example.kafka.service

import com.example.kafka.dto.ProductEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String> //kafkaTemplate은 뭐야?
// KafkaTemplate은 Kafka에 메시지를 보내기 위한 템플릿이다. KafkaTemplate은 ProducerFactory를 사용하여 KafkaProducer를 생성하고 KafkaProducer를 사용하여 메시지를 보낸다.
) {

    private final val TOPIC = "product-event"

    fun sendMessage(event: ProductEvent):Unit {
        kafkaTemplate.send(TOPIC, event.productSellId, event.toString()) // String topic, K key, @Nullable V data }
    }
}