package ru.vood.graph.haselcastgraphservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HazelcastGraphServiceApplication

fun main(args: Array<String>) {
    runApplication<HazelcastGraphServiceApplication>(*args)
}
