package ru.tinkoff.fintech.lesson6.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class RestTemplateConfiguration {

    @Value("\${connect.timeout.in.sec}") private val connectTimeout : Long = 0
    @Value("\${read.timeout.in.seconds}") private val readTimeout : Long = 0

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder
        .setConnectTimeout(Duration.ofSeconds(connectTimeout.toLong()))
        .setReadTimeout(Duration.ofSeconds(readTimeout))
        .build()
}

