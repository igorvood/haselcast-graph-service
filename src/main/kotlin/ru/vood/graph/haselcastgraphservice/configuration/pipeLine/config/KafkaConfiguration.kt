package ru.vood.graph.haselcastgraphservice.configuration.pipeLine.config

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
open class KafkaConfiguration {

    @Bean
    open fun kafkaProps(): Properties {
        val props = Properties()
        props.setProperty("bootstrap.servers", "localhost:9092")
        props.setProperty("key.deserializer", StringDeserializer::class.java.canonicalName)
        props.setProperty("value.deserializer", StringDeserializer::class.java.canonicalName)
        props.setProperty("key.serializer", StringSerializer::class.java.canonicalName)
        props.setProperty("value.serializer", StringSerializer::class.java.canonicalName)
        props.setProperty("auto.offset.reset", "earliest")
        return props

    }
}