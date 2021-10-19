package ru.vood.graph.haselcastgraphservice.dto.meta

import ru.vood.graph.haselcastgraphservice.dto.ClientJob
import ru.vood.graph.haselcastgraphservice.dto.dsl.MetaEntity
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.genEntityData
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.stdBool
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.stdDate
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.stdNum
import ru.vood.graph.haselcastgraphservice.dto.dsl.StandardFunction.stdStr
import ru.vood.graph.haselcastgraphservice.dto.dsl.entity
import ru.vood.graph.haselcastgraphservice.dto.dsl.genVal

object ClientMeta {
    fun standardMeta(): MetaEntity<String> {
        val clientMeta by entity<String> {
            val id by string() genVal { q, w -> q.id.value() }
            val name by string() genVal stdStr()
            val goodClient by bool() genVal stdBool()
            val salary by number() genVal stdNum()
            val birthDate by date() genVal stdDate()


            val jobSet by set<ClientJob>() genVal { et, pn ->
                genEntityData(
                    ClientJobMeta.standardMeta(),
                    { IntRange(1, 10).map { """${et.id.value()}_$it""" }.toSet() },
                    { pk, meta -> ClientJob(pk, meta) })
            }


        }
        return clientMeta
    }
}