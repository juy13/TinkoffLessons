package ru.tinkoff.fintech.lesson10.student.service

import ru.tinkoff.fintech.lesson10.interfaces.NotificationService
import ru.tinkoff.fintech.lesson10.repository.JpaEventRepositoryImpl

class PushService(private val jpaEventRepositoryImpl: JpaEventRepositoryImpl) : NotificationService {

    override fun push(message: String, id: Int) {
        println("Push $message")
        jpaEventRepositoryImpl.doneEvent(id)
    }
}