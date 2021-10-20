package ru.vood.graph.haselcastgraphservice.emulate.schedule

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.vood.graph.haselcastgraphservice.emulate.queue.ExternalData.calculationQueue
import kotlin.math.abs
import kotlin.random.Random

@Component
class CalculationRequest {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(CalculationRequest::class.java)

    }

    private val random = Random(1000000000L)

    @Scheduled(fixedRate = 10000)
    fun createRequest() {
        val calculationId = abs(random.nextInt())
        logger.info("Request calculation $calculationId")
        calculationQueue.add(calculationId.toString())
    }
}