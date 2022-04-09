package ru.tinkoff.fintech.lesson6.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(basePackages = ["ru.tinkoff.fintech.lesson6"])
class JbcConfiguration {

    @Bean
    fun jbcTemplate(dataSource : DataSource) : JdbcTemplate = JdbcTemplate(dataSource)
}