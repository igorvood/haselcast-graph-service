package ru.vood.graph.haselcastgraphservice.dto

import ru.vood.graph.haselcastgraphservice.dto.dsl.EntityTemplate
import ru.vood.graph.haselcastgraphservice.dto.dsl.MetaEntity

class Client(id: String, meta: MetaEntity<String>) :
    EntityTemplate<String>(id, meta)