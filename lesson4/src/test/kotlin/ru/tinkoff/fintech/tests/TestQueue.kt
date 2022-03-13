package ru.tinkoff.fintech.tests

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.tinkoff.fintech.lesson4.NoSuchElementException
import ru.tinkoff.fintech.lesson4.Queue
import ru.tinkoff.fintech.lesson4.Stack
import kotlin.test.assertEquals

class TestQueue {

    @Test
    fun `test queue for offer` () {
        val queue = Queue<Int>()
        assertEquals(true, queue.offer((3)))
    }

    @Test
    fun `test queue for element` () {
        val queue = Queue<Int>()
        queue.offer((3))
        assertEquals(3, queue.element())
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
        assertEquals(3, queue.remove())
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
        assertEquals(3, queue.peek())
    }

    @Test
    fun `test queue for peek Null` () {
        val queue = Queue<Int>()
        assertEquals(null, queue.peek())
    }

    @Test
    fun `test queue for poll` () {
        val queue = Queue<Int>()
        queue.offer((3))
        assertEquals(3, queue.poll())
    }

    @Test
    fun `test queue for poll Null` () {
        val queue = Queue<Int>()
        assertEquals(null, queue.poll())
    }

    @Test
    fun `test queue for push 5 elements` () {
        val stack = Queue<Int>().apply {
            offer(3)
            offer(5)
            offer(6)
            offer(7)
            offer(1)
        }
        assertEquals(5, stack.size())
    }

    @Test
    fun `test queue for 5 elements` () {
        val stack = Queue<Int>().apply {
            offer(3)
            offer(5)
            offer(6)
            offer(7)
            offer(1)
        }
        val stackArray = arrayOf(3, 5, 6, 7, 1)
        for (el in stackArray) {
            assertEquals(el, stack.remove())
        }
    }

}