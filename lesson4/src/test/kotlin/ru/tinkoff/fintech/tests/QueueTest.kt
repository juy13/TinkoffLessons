package ru.tinkoff.fintech.tests

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.tinkoff.fintech.lesson4.NoSuchElementException
import ru.tinkoff.fintech.lesson4.Queue
import kotlin.test.assertEquals

class QueueTest {

    @Test
    fun `test queue for offer` () {
        val queue = Queue<Int>()
        val offer = queue.offer(3)
        assertEquals(true, offer)
    }

    @Test
    fun `test queue for element` () {
        val queue = Queue<Int>()
        queue.offer((3))
        val element = queue.element()
        assertEquals(3, element)
    }

    @Test
    fun `test queue for element Ex` () {
        val queue = Queue<Int>()
        assertThrows<NoSuchElementException> {queue.element()}
    }

    @Test
    fun `test queue for remove` () {
        val queue = Queue<Int>()
        queue.offer((3))
        val element = queue.remove()
        assertEquals(3, element)
    }

    @Test
    fun `test queue for remove Ex` () {
        val queue = Queue<Int>()
        assertThrows<NoSuchElementException> {queue.remove()}
    }

    @Test
    fun `test queue for peek` () {
        val queue = Queue<Int>()
        queue.offer((3))
        val element = queue.peek()
        assertEquals(3, element)
    }

    @Test
    fun `test queue for peek Null` () {
        val queue = Queue<Int>()
        val element = queue.peek()
        assertEquals(null, element)
    }

    @Test
    fun `test queue for poll` () {
        val queue = Queue<Int>()
        queue.offer((3))
        val element = queue.poll()
        assertEquals(3, element)
    }

    @Test
    fun `test queue for poll Null` () {
        val queue = Queue<Int>()
        val element = queue.poll()
        assertEquals(null, element)
    }

    @Test
    fun `test queue for push 5 elements` () {
        val queue = Queue<Int>().apply {
            offer(3)
            offer(5)
            offer(6)
            offer(7)
            offer(1)
        }
        val size = queue.size()
        assertEquals(5, size)
    }

    @Test
    fun `test queue for 5 elements` () {
        val queue = Queue<Int>().apply {
            offer(3)
            offer(5)
            offer(6)
            offer(7)
            offer(1)
        }
        val stackArray = arrayOf(3, 5, 6, 7, 1)
        for (el in stackArray) {
            val element = queue.remove()
            assertEquals(el, element)
        }
    }

}