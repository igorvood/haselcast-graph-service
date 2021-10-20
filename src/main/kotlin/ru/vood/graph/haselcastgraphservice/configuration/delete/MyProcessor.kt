package ru.vood.graph.haselcastgraphservice.configuration.delete

import com.hazelcast.jet.core.AbstractProcessor
import com.hazelcast.jet.pipeline.Pipeline
import com.hazelcast.jet.pipeline.Sinks

import com.hazelcast.jet.pipeline.test.TestSources




class MyProcessor: AbstractProcessor() {
fun sadsad(){
    val p: Pipeline = Pipeline.create()
    val withoutTimestamps = p.readFrom(TestSources.itemStream(100))
        .withoutTimestamps()





    withoutTimestamps
        .writeTo(
            Sinks.filesBuilder<Any>("out")
                .rollByDate("YYYY-MM-dd.HH")
                .build()
        )
}
}