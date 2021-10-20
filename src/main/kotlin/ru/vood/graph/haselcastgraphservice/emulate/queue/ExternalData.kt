package ru.vood.graph.haselcastgraphservice.emulate.queue

import java.util.concurrent.LinkedBlockingQueue

object ExternalData {
    val calculationQueue: LinkedBlockingQueue<String> = LinkedBlockingQueue(100000)
}