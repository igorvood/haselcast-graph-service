package ru.vood.graph.haselcastgraphservice.kafka

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, SomeData>) {
    
    fun send(topic: String, payload: String) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic)
        kafkaTemplate.send(topic, LocalDateTime.now().toString(), SomeData(payload))
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(KafkaProducer::class.java)
    }
}