package com.example.isuservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class IsuServiceApplication

fun main(args: Array<String>) {
    runApplication<IsuServiceApplication>(*args)
}

@Configuration
class RestConf() {
    @Bean
    fun template() = RestTemplate()

    @Bean
    fun mapper(): ObjectMapper = ObjectMapper().registerKotlinModule().findAndRegisterModules()
}