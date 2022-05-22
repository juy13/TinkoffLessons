package ru.tinkoff.fintech.lesson10.student.service

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson10.interfaces.NotificationService
import ru.tinkoff.fintech.lesson10.repository.JpaEventRepositoryImpl

class SMSService (private val jpaEventRepositoryImpl: JpaEventRepositoryImpl) : NotificationService {

    override fun push(message: String, id : Int) {
        println("SMS $message")
        jpaEventRepositoryImpl.doneEvent(id)
    }
}