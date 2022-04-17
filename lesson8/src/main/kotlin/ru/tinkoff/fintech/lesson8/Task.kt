package ru.tinkoff.fintech.lesson8

import java.util.*

class Task(private val name: String) : Runnable {

    override fun run() {
        println("Executing : " + name + ", Current Seconds : " + Date().seconds + " ${Thread.currentThread().name}")
    }

    fun getName(): String {
        return name
    }
}
