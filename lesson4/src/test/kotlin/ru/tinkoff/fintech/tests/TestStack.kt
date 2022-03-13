package ru.tinkoff.fintech.tests

import io.mockk.InternalPlatformDsl.toArray
import org.junit.jupiter.api.Test
import ru.tinkoff.fintech.lesson4.Stack
import kotlin.test.assertEquals

class TestStack {

    @Test
    fun `test stack for push` () {
        val stack = Stack<Int>()
        assertEquals(true, stack.push(3))
    }

    @Test
    fun `test stack for push 5 elements` () {
        val stack = Stack<Int>().apply {
            push(3)
            push(5)
            push(6)
            push(7)
            push(1)
        }
        assertEquals(5, stack.size())
    }

    @Test
    fun `test stack for 5 elements` () {
        val stack = Stack<Int>().apply {
            push(3)
            push(5)
            push(6)
            push(7)
            push(1)
        }
        val stackArray = arrayOf(3, 5, 6, 7, 1)
        stackArray.reverse()
        for (el in stackArray) {
            assertEquals(el, stack.pop())
        }
    }

    @Test
    fun `test stack for pop` () {
        val stack = Stack<Int>()
        stack.push(3)
        assertEquals(3, stack.pop())
    }

    @Test
    fun `test stack for pop Null` () {
        val stack = Stack<Int>()
        assertEquals(null, stack.pop())
    }

    @Test
    fun `test stack for peek` () {
        val stack = Stack<Int>()
        stack.push(3)
        assertEquals(3, stack.peek())
    }

    @Test
    fun `test stack for peek Null` () {
        val stack = Stack<Int>()
        assertEquals(null, stack.peek())
    }

}