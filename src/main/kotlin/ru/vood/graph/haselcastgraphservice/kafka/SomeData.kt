package ru.vood.graph.haselcastgraphservice.kafka

data class SomeData (
    val s: String,
    val w: Int = 0
):org.apache.kafka.common.serialization.StringSerializer(){

}
