package com.example.studygroup.configuration

import interfaces.TryInterface
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
        jndiProps["java.naming.factory.initial"] = "org.jboss.naming.remote.client.InitialContextFactory"
        jndiProps["jboss.naming.client.ejb.context"] = true
        jndiProps["java.naming.provider.url"] = "http-remoting://localhost:9990"
        return InitialContext(jndiProps)
    }

    @Bean
    @Throws(NamingException::class)
    fun tryStatelessBean(context: Context): TryInterface? {
        return context.lookup(this.getFullName(TryInterface::class.java)) as TryInterface?
    }

    private fun getFullName(classType: Class<*>): String {
        val moduleName = "ejb-remote-for-spring/"
        val beanName = classType.simpleName
        val viewClassName = classType.name
        return "$moduleName$beanName!$viewClassName"
    }
}