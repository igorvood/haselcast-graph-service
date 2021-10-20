package ru.vood.graph.haselcastgraphservice.dto

import org.springframework.stereotype.Service
import ru.vood.graph.haselcastgraphservice.dto.meta.ClientMeta.clientMeta

@Service
class GenerateData {

    fun getClient(id: String): Client = Client(id,clientMeta)

    fun getJobClient(id: String): Set<ClientJob> {
        val client = getClient(id)
        val first = client.meta.property.filter { it.name == "jobSet" }.map { it.function(client, it.name) }
            .first()
        return first.value() as Set<ClientJob>

    }
}