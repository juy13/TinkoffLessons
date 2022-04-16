package ru.tinkoff.fintech.lesson8

class WorkerThread : Thread() {
    
    override fun run() {
        println("This code is running in a thread")
    }
}