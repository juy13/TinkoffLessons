package ru.tinkoff.fintech.lesson10.student.service

import ru.tinkoff.fintech.lesson10.configuration.Service
import ru.tinkoff.fintech.lesson10.repository.JpaEventRepositoryImpl

class SMSService (private val jpaEventRepositoryImpl: JpaEventRepositoryImpl) : Service {

    override fun push(message: String, id : Int) {
        println("SMS $message")
        jpaEventRepositoryImpl.doneEvent(id)
    }
}