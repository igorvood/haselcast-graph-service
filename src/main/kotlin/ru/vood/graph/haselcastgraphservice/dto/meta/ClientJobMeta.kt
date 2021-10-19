package ru.vood.graph.haselcastgraphservice.dto.meta

import ru.vood.graph.haselcastgraphservice.dto.dsl.MetaEntity
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.genEntityData
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.stdBool
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.stdDate
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.stdNum
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.stdStr
import ru.vood.graph.haselcastgraphservice.dto.dsl.entity
import ru.vood.graph.haselcastgraphservice.dto.dsl.genVal

object ClientJobMeta {
    fun standardMeta(): MetaEntity<String> {
        val clientJobMeta by entity<String> {
            val id by string() genVal { q, w -> q.id.value() }
            val name by string() genVal stdStr()
            val goodJob by bool() genVal stdBool()
            val salary by number() genVal stdNum()
        }
        return clientJobMeta
    }
}