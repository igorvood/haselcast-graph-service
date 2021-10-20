package ru.vood.graph.haselcastgraphservice.dto

import ru.vood.graph.haselcastgraphservice.dto.dsl.EntityTemplate
import ru.vood.graph.haselcastgraphservice.dto.dsl.MetaEntity
import java.math.BigDecimal
import java.time.LocalDateTime

class Client(id: String, meta: MetaEntity<String>) :
    EntityTemplate<String>(id, meta) {
    val name = fieldValue<String>("name")
    val goodClient = fieldValue<Boolean>("goodClient")
    val salary = fieldValue<BigDecimal>("salary")
    val age = fieldValue<BigDecimal>("age")

    val birthDate = fieldValue<LocalDateTime>("birthDate")
    val jobSet = fieldValue<Set<ClientJob>>("jobSet")
}