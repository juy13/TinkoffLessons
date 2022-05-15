package ru.tinkoff.fintech.lesson10.student.service

import ru.tinkoff.fintech.lesson10.configuration.Service
import ru.tinkoff.fintech.lesson10.repository.JpaEventRepositoryImpl

class PushService(private val jpaEventRepositoryImpl: JpaEventRepositoryImpl) : Service {

    override fun push(message: String, id: Int) {
        println("Push $message")
        jpaEventRepositoryImpl.doneEvent(id)
    }
}