package ru.vood.graph.haselcastgraphservice.dto

import org.springframework.stereotype.Service
import ru.vood.graph.haselcastgraphservice.dto.meta.ClientMeta.clientMeta
import java.io.Serializable

@Service
class GenerateData: Serializable {

    fun getClient(id: String): Client = Client(id, clientMeta)

    fun getJobClient(id: String): Set<ClientJob> {
        val client = getClient(id)
        val first = client.meta.property.filter { it.name == "jobSet" }.map { it.function(client, it.name) }
            .first()
        return first.value() as Set<ClientJob>

    }
}