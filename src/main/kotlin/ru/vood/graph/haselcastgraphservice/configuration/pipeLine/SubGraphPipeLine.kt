package ru.vood.graph.haselcastgraphservice.configuration.pipeLine

import com.hazelcast.jet.JetInstance
import com.hazelcast.jet.config.JobConfig
import com.hazelcast.jet.pipeline.Pipeline
import org.springframework.beans.factory.annotation.Autowired

interface SubGraphPipeLine {

    fun build(): Pipeline

    fun cfg(): JobConfig

    @Autowired
    fun registerPipeLine(jet: JetInstance) {
        jet.newJob(build(), cfg())
    }
}