package ru.tinkoff.fintech.lesson10.student.service

import ru.tinkoff.fintech.lesson10.configuration.Service
import ru.tinkoff.fintech.lesson10.repository.JpaEventRepositoryImpl

class EmailService(private val jpaEventRepositoryImpl: JpaEventRepositoryImpl) : Service {

    override fun push(message: String, id: Int) {
        println("Email $message")
        jpaEventRepositoryImpl.doneEvent(id)
    }
}