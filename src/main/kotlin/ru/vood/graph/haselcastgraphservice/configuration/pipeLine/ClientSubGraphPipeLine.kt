package ru.vood.graph.haselcastgraphservice.configuration.pipeLine

import com.hazelcast.jet.config.JobConfig
import com.hazelcast.jet.pipeline.Pipeline
import com.hazelcast.jet.pipeline.Sinks
import com.hazelcast.jet.pipeline.Sources
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.vood.graph.haselcastgraphservice.dto.GenerateData
import java.io.Serializable

@Component
class ClientSubGraphPipeLine : SubGraphPipeLine, Serializable {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(ClientSubGraphPipeLine::class.java)
    }

    @Autowired
    lateinit var generateData: GenerateData

    override fun build(): Pipeline {

        val pipeline = Pipeline.create()
        val readFrom = pipeline.readFrom(Sources.fileWatcher("D:\\temp\\1"))
        val mainStream = readFrom
            .withoutTimestamps()
        val clientCheckStream = mainStream
            .setName("clientCheckStream")
            .map { CheckResult(generateData.getClient(it)) }


        val goodClientStream = clientCheckStream
            .setName("goodClient")
            .map {
                val (cl, ck) = it
                ck.add(Check("goodClient", cl.goodClient))
                it
            }

        val moreThan18 = clientCheckStream
            .setName("more than 18")
            .map {
                val (cl, ck) = it
                ck.add(Check("more than 18", cl.age.toInt() >= 18))
                it
            }

        clientCheckStream
            .merge(goodClientStream)
            .merge(moreThan18)
            .map { "Client " + it.ent.id.value() + "\n" + it.res.joinToString("\n") { ck -> ck.nameCheck + " -> " + ck.res } + "\n=======================================" }
            .writeTo(Sinks.files("D:\\temp\\res"))
        return pipeline
    }

    override fun cfg(): JobConfig {
        return JobConfig()
    }
}