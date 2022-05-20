package ru.tinkoff.fintech.lesson10.interfaces

interface NotificationService {

    fun push(message : String, id : Int)

}