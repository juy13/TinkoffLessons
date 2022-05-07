package ru.tinkoff.fintech.tests

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tinkoff.fintech.lesson8.ThreadPool
import java.lang.Thread.sleep
import java.util.concurrent.CountDownLatch


class ThreadTest {

    @Test
    @Throws(InterruptedException::class)
    fun `test counter`() {
        val numberOfThreads = 5
        val service = ThreadPool(5)
        val latch = CountDownLatch(numberOfThreads)
        var counter = 0
        for (i in 0 until numberOfThreads) {
            service.execute {
                counter += 1
                latch.countDown()
            }
        }
        latch.await()
        service.shutdown()
        assertEquals(numberOfThreads, counter)
    }

    @Test
    fun `test pool size`() {
        val service = ThreadPool(10)
        service.shutdown()

        assertEquals(5, service.getSizeOfQueue())
    }

    @Test
    fun `test shutdown service`() {
        val service = ThreadPool(10)
        var counter = 0
        service.shutdown()
        service.execute {
            counter += 1
        }

        assertEquals(0, counter)
    }

    @Test
    fun `test counter before shutdown service`() {
        val service = ThreadPool(4)
        var counter = 0
        val latch = CountDownLatch(40)
        for (i in 1..40) {
            service.execute {
                counter += 1
                latch.countDown()
                sleep(100)
            }
        }

        service.shutdown()
        latch.await()
        assertEquals(40, counter)
    }
}