package ru.vood.graph.haselcastgraphservice.configuration

import com.hazelcast.jet.Jet
import com.hazelcast.jet.JetInstance
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class JetConfiguration {

    @Bean
    open fun instance(): JetInstance = Jet.newJetInstance()

}