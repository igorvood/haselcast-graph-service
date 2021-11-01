package ru.vood.graph.haselcastgraphservice.configuration.pipeLine

import com.hazelcast.jet.kafka.KafkaSinks
import com.hazelcast.jet.kafka.KafkaSources
import com.hazelcast.jet.pipeline.Pipeline
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class KafkaJobSubGraphPipeLine(
    val kafkaProps:Properties
) : SubGraphPipeLine {

    override fun build(): Pipeline {
        val p = Pipeline.create()

        val name = PipeLines.JOBS.name
        p.readFrom(KafkaSources.kafka<String, String>(kafkaProps, name))
            .withoutTimestamps()
            .map { q ->
                logger.info("Запустил пайп топика $name $q")
                q
            }
            .writeTo(KafkaSinks.kafka(kafkaProps, "PipeLines.JOBS.name"))

        return p
    }


    companion object {
        val logger: Logger = LoggerFactory.getLogger(ClientSubGraphPipeLine::class.java)
    }
}