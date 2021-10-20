package ru.vood.graph.haselcastgraphservice.configuration.pipeLine

data class  CheckResult<T>(
  val ent: T,
  val res: MutableSet<Check> = mutableSetOf()
)
