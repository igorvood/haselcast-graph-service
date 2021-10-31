package ru.vood.graph.haselcastgraphservice.kafka

import org.apache.kafka.common.serialization.Serializer

class CustomSerializer: Serializer<Any> {

    override fun serialize(topic: String?, data: Any?): ByteArray {
        TODO("Not yet implemented")
    }
}