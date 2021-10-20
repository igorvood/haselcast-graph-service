package ru.vood.graph.haselcastgraphservice.configuration.pipeLine

import com.hazelcast.cluster.Address
import com.hazelcast.function.FunctionEx
import com.hazelcast.internal.util.Preconditions
import com.hazelcast.jet.Util.entry
import com.hazelcast.jet.config.JobConfig
import com.hazelcast.jet.core.*
import com.hazelcast.jet.impl.pipeline.transform.StreamSourceTransform
import com.hazelcast.jet.kafka.KafkaProcessors
import com.hazelcast.jet.kafka.KafkaSources
import com.hazelcast.jet.pipeline.Pipeline
import com.hazelcast.jet.pipeline.Sources
import com.hazelcast.jet.pipeline.StreamSource
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

@Component
class ClientSubGraphPipeLine : SubGraphPipeLine {

    fun <K, V, T> kafka(
        properties: Properties?,
        projectionFn: FunctionEx<ConsumerRecord<K, V>?, T>,
        vararg topics: String
    ): StreamSource<T> {
        Preconditions.checkPositive(topics.size.toDouble(), "At least one topic required")
        return Sources.streamFromProcessorWithWatermarks(
            "kafkaSource(" + java.lang.String.join(",", *topics) + ")",
            true
        ) { w: EventTimePolicy<in T> ->
            KafkaProcessors.streamKafkaP(
                properties!!, projectionFn, w, *topics
            )
        }
    }

    fun <T> streamFromProcessorWithWatermarks(
        sourceName: String,
        supportsNativeTimestamps: Boolean,
        metaSupplierFn: FunctionEx<EventTimePolicy<in T>, ProcessorMetaSupplier>
    ): StreamSource<T> {
        return StreamSourceTransform(sourceName, metaSupplierFn, true, supportsNativeTimestamps)
    }


    override fun build(): Pipeline {
        val create = Pipeline.create()
        TradeSource
        StreamSource.
//        Sources.streamFromProcessorWithWatermarks(
//            "My first Stream",
//            true,
//
//
//        )

        val entry = entry("1", 1)
        KafkaSources.kafka<String, Int>(Properties(), "asd")
//        EventTimePolicy.eventTimePolicy<>()
////        object FunctionEx()
//
        val streamSourceTransform = StreamSourceTransform(
            "client stream",
            { q: EventTimePolicy<in String> ->
                val wrapFn = q.wrapFn()
                object : ProcessorMetaSupplier {
                    override fun get(addresses: MutableList<Address>): Function<in Address, out ProcessorSupplier> {
                        object : ProcessorSupplier {
                            override fun get(count: Int): MutableCollection<out Processor> {
//                                object : AbstractProcessor()
                                val s = object : AbstractProcessor(){}
                                return mutableListOf(
                                    object : Processor {
                                    override fun tryProcessWatermark(watermark: Watermark): Boolean {
                                        return watermark.timestamp() != null
                                    }
                                }
                                )
                            }
                        }
                        TODO("Not yet implemented")
                    }
                }
                TODO("Not yet implemented")
            },
            true,
            false
        )
//
        create.readFrom(streamSourceTransform)
        return create

    }

    override fun cfg(): JobConfig {
        return JobConfig()
    }
}