package ru.tinkoff.fintech.lesson10.student.service

import ru.tinkoff.fintech.lesson10.interfaces.NotificationService
import ru.tinkoff.fintech.lesson10.repository.JpaEventRepositoryImpl

class EmailService(private val jpaEventRepositoryImpl: JpaEventRepositoryImpl) : NotificationService {

    override fun push(message: String, id: Int) {
        println("Email $message")
        jpaEventRepositoryImpl.doneEvent(id)
    }
}