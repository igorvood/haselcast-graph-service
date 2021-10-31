package ru.vood.graph.haselcastgraphservice.kafka

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import java.util.concurrent.TimeUnit


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = ["listeners=PLAINTEXT://localhost:9092", "port=9092"])
internal class EmbeddedKafkaIntegrationTest {
    @Autowired
    private lateinit var consumer: KafkaConsumer

    @Autowired
    private lateinit var producer: KafkaProducer

    @Value("\${test.topic}")
    private lateinit var topic: String

    @Test
    fun givenEmbeddedKafkaBroker_whenSendingtoSimpleProducer_thenMessageReceived() {
        producer.send(topic, "Sending with own simple KafkaProducer")
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS)
        Assertions.assertEquals(consumer.getLatch().count, 0L)
        Assertions.assertTrue(consumer.payload!!.contains("embedded-test-topic"))
    }
}