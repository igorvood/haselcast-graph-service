package ru.vood.graph.haselcastgraphservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class HazelcastGraphServiceApplication

fun main(args: Array<String>) {
    runApplication<HazelcastGraphServiceApplication>(*args)
}
