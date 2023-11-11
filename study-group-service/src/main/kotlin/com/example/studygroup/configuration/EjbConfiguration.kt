package com.example.studygroup.configuration

import TryInterface
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
        val className = TryInterface::class.java.name
        return context.lookup("ejb:/ejb-service/TryEjb!$className") as TryInterface?
    }

    private fun getFullName(classType: Class<*>): String {
        val moduleName = "ejb:/"
        val beanName = classType.simpleName
        val viewClassName = classType.name
        return "$moduleName$beanName!$viewClassName"
    }
}