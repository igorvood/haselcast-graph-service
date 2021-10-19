package ru.vood.graph.haselcastgraphservice.dto.dsl

interface DataType<out T> {

    fun value(): T
}