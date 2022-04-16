package ru.tinkoff.fintech.lesson8

import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue




class ThreadPool(amountOfThreads: Int) : Executor {

    private val amountOfThreads = 5
    private var workingThreads = 0
    private var threads = 0
    private val lbqOfThreads = LinkedBlockingQueue<WorkerThread>()

    override fun execute(command: Runnable) {

    }

    init {
        if (amountOfThreads <= this.amountOfThreads) {
            this.threads = amountOfThreads
            for (it in 1..this.threads) {
                val thread = WorkerThread()
                thread.start()
                lbqOfThreads.add(thread)
            }
        }
    }
}