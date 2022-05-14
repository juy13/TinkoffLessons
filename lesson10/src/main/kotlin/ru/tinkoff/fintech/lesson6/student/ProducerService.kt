package ru.tinkoff.fintech.lesson6.student

import org.springframework.scheduling.annotation.Scheduled
import java.util.*


class ProducerService {

    @Scheduled(cron = "*/5 * * * * ?")
    fun produce() {
        println("Method executed at every 5 seconds. Current time is :: " + Date())
    }
}