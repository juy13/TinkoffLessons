package ru.tinkoff.fintech.lesson8

import java.lang.Thread.sleep


fun main() {

    val customThreadPool = ThreadPool(4)

    for (i in 1..40) {
        val task = Task("Task $i")
//        sleep(30)
        println("Created : " + task.getName())
        customThreadPool.execute(task)
    }
//    sleep(500)

    customThreadPool.shutdown()
}