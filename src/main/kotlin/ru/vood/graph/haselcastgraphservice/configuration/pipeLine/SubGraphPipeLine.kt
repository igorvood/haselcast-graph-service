package ru.vood.graph.haselcastgraphservice.configuration.pipeLine

import com.hazelcast.jet.JetInstance
import com.hazelcast.jet.config.JobConfig
import com.hazelcast.jet.pipeline.Pipeline
import org.springframework.beans.factory.annotation.Autowired
import java.io.Serializable

interface SubGraphPipeLine: Serializable {

    fun build(): Pipeline

    fun cfg(): JobConfig = JobConfig()

    @Autowired
    fun registerPipeLine(jet: JetInstance) {
        jet.newJob(build(), cfg())
    }
}