package ru.vood.graph.haselcastgraphservice.dto.dsl

interface Builder<T> {
    fun build(): T
}