package ru.tinkoff.fintech.lesson8

import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue

class ThreadPool(private val poolSize: Int = 5) : Executor {

    private var workers: Array<WorkerThread?>
    private val queue: LinkedBlockingQueue<Runnable> = LinkedBlockingQueue()
    private var pSize :  Int = 0

    init {
        pSize = if (poolSize <= 5) poolSize else 5
        workers = arrayOfNulls(pSize)
        for (i in 0 until pSize) {
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

    fun getSizeOfQueue() = workers.size

    fun shutdown() {
        //println("Shutting down")
        for (i in 0 until pSize) {
            workers[i]?.workUntilAlive = false
        }
        synchronized(queue as Object) {
            queue.notifyAll()
        }
    }
}