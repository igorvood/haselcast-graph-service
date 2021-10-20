package ru.vood.graph.haselcastgraphservice.configuration.pipeLine

import com.hazelcast.jet.config.JobConfig
import com.hazelcast.jet.pipeline.Pipeline
import com.hazelcast.jet.pipeline.Sinks
import com.hazelcast.jet.pipeline.Sources
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ClientSubGraphPipeLine : SubGraphPipeLine {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(ClientSubGraphPipeLine::class.java)
    }

    override fun build(): Pipeline {

        val pipeline = Pipeline.create()
        val readFrom = pipeline.readFrom(Sources.fileWatcher("D:\\temp\\1"))
        val withoutTimestamps = readFrom
            .withoutTimestamps()

        val filter = withoutTimestamps.setName("devide by 2").map { it.toInt() }.filter { it % 2 == 0 }.map { it.toString() }

//        val filter1 = withoutTimestamps.setName("devide by 3").map { it.toInt() }.filter { it % 3 == 0 }.map { it.toString() }



        withoutTimestamps
            .merge(filter)
//            .merge(filter1)
            .peek { q ->
                logger.info("new string added $q")
                q
            }
            .writeTo(Sinks.logger());
        return pipeline
    }

    override fun cfg(): JobConfig {
        return JobConfig()
    }
}