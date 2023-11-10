package com.example.isuservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.client5.http.io.HttpClientConnectionManager
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory
import org.apache.hc.core5.ssl.SSLContexts
import org.apache.hc.core5.ssl.TrustStrategy
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext


@SpringBootApplication
@EnableDiscoveryClient
class IsuServiceApplication

fun main(args: Array<String>) {
    runApplication<IsuServiceApplication>(*args)
}

@Configuration
class RestConf() {


    @Bean
    @LoadBalanced
    fun restTemplate(): RestTemplate {
        val acceptingTrustStrategy =
            TrustStrategy { _: Array<X509Certificate?>?, _: String? -> true }
        val sslContext: SSLContext = SSLContexts.custom()
            .loadTrustMaterial(null, acceptingTrustStrategy)
            .build()
        val sslConFactory = SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier())
        val cm: HttpClientConnectionManager = PoolingHttpClientConnectionManagerBuilder.create()
            .setSSLSocketFactory(sslConFactory)
            .build();

        val httpClient: CloseableHttpClient = HttpClients.custom()
            .setConnectionManager(cm)
            .evictExpiredConnections()
            .build();

        val requestFactory: ClientHttpRequestFactory = HttpComponentsClientHttpRequestFactory(httpClient)
        return RestTemplate(requestFactory)
    }


    @Bean
    fun mapper(): ObjectMapper = ObjectMapper().registerKotlinModule().findAndRegisterModules()
}