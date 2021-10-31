package ru.vood.graph.haselcastgraphservice.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch


@Component
class KafkaConsumer {
    private val latch: CountDownLatch = CountDownLatch(1)
    lateinit var payload: String

    @KafkaListener(topics = ["\${test.topic}"])
    fun receive(consumerRecord: ConsumerRecord<String, SomeData>) {
        LOGGER.info("received payload='{}'", consumerRecord.toString())
        val key = consumerRecord.key()
        val value = consumerRecord.value()
        payload = consumerRecord.toString()
        latch.countDown()
    }

    fun getLatch(): CountDownLatch {
        return latch
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)
    }
}