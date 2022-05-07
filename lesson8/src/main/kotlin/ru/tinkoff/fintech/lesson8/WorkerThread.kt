package ru.tinkoff.fintech.lesson8

import java.util.concurrent.LinkedBlockingQueue

class WorkerThread(private val queue: LinkedBlockingQueue<Runnable>) : Thread() {

    var workUntilAlive = true

    override fun run() {
        var task: Runnable?

        while (queue.isNotEmpty() or workUntilAlive) {
            synchronized(queue as Object) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait()
                    } catch (e: InterruptedException) {
                        println("An error occurred while queue is waiting: " + e.message)
                    }
                    if(!workUntilAlive)
                        break
                }
                task = queue.poll()
            }
            try {
                if (task != null) {
                    (task as Runnable).run()
                }
            } catch (e: RuntimeException) {
                println("Thread pool is interrupted due to an issue: " + e.message)
            }
        }
        println("Stop thread ${currentThread().name}")
    }
}