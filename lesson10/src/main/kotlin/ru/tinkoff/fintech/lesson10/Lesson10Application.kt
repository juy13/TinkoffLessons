package ru.tinkoff.fintech.lesson10

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class Lesson10Application

fun main() {
	runApplication<Lesson10Application>()
}
