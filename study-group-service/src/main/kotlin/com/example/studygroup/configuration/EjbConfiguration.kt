package com.example.studygroup.configuration

import ejbs.TryEjb
import interfaces.TryInterface
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.wildfly.naming.client.WildFlyInitialContextFactory
import java.util.*
import javax.naming.Context
import javax.naming.InitialContext
import javax.naming.NamingException

@Configuration
class EjbConfiguration {

    @Bean
    @Throws(NamingException::class)
    fun context(): Context? {
        val jndiProps = Properties()
        jndiProps[Context.INITIAL_CONTEXT_FACTORY] = WildFlyInitialContextFactory::class.java.name;
        jndiProps[Context.PROVIDER_URL] = "remote+http://localhost:8080";
        return InitialContext(jndiProps)
    }

    @Bean
    @DependsOn("context")
    @Throws(NamingException::class)
    fun tryStatelessBean(context: Context): TryInterface? {
        return context.lookup(this.getFullName(TryEjb::class.java, TryInterface::class.java)) as TryInterface?
    }

    private fun getFullName(classType: Class<*>, interfaceType: Class<*>): String {
        val moduleName = "ejb:/ejb-service"
        val beanName = classType.name
        val viewClassName = interfaceType.name
        return "$moduleName/$beanName!$viewClassName"
    }
}