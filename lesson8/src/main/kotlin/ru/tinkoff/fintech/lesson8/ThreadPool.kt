package ru.tinkoff.fintech.lesson8

import java.util.Objects
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

class ThreadPool(private val poolSize: Int = 5) : Executor {

    private val workers: Array<WorkerThread?> = arrayOfNulls(poolSize)
    private val queue: LinkedBlockingQueue<Runnable> = LinkedBlockingQueue()

    init {
        for (i in 0 until if (poolSize <= 5) poolSize else 5) {
            workers[i] = WorkerThread(queue)
            workers[i]!!.start()
        }
    }

    override fun execute(task: Runnable) {
        synchronized(queue as Object) {
            queue.add(task)
            queue.notify()
        }
    }

    fun shutdown() {
        println("Shutting down")
        for (i in 0 until poolSize) {
            workers[i]?.workUntilAlive = false
        }
        synchronized(queue as Object) {
            queue.notifyAll()
        }
    }
}