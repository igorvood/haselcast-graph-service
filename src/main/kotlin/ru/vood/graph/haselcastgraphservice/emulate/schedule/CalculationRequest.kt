package ru.vood.graph.haselcastgraphservice.emulate.schedule

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import ru.vood.graph.haselcastgraphservice.dto.GenerateData
import java.io.File
import javax.annotation.PostConstruct
import kotlin.math.abs
import kotlin.random.Random

//@Component
@Deprecated("Была проба на файлах")
class CalculationRequest {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(CalculationRequest::class.java)

    }

    @Autowired
    lateinit var generateData: GenerateData

    private val random = Random(1000000000L)
    private val file = File("D:\\temp\\1\\Stream.file")
//    private val file2 = File("D:\\temp\\2\\Stream.file")

    @PostConstruct
    private fun deleteFile() {
        file.deleteOnExit()
    }

    @Scheduled(fixedRate = 10000)
    fun createRequest() {

        val calculationId = abs(random.nextInt())
        logger.info("Request calculation $calculationId")
        file.appendText(calculationId.toString() + "\n")

//        val calculationId2 = abs(random.nextInt(15))
//        logger.info("Request calculation $calculationId2")
//        file2.appendText(calculationId.toString()+"\n")

//        calculationQueue.add(calculationId.toString())
    }
}