package ru.vood.graph.haselcastgraphservice.configuration.pipeLine

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import ru.vood.graph.haselcastgraphservice.kafka.KafkaProducer

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = ["listeners=PLAINTEXT://localhost:9092", "port=9092"])
internal class KafkaClientSubGraphPipeLineTest {

    @Autowired
    private lateinit var producer: KafkaProducer


    @Test
    fun build() {
        IntRange(0,1).forEach { producer.send(PipeLines.CLIENTS.name, "asdasdasdasdasd $it") }

    }
}