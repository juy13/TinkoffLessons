package ru.tinkoff.fintech.tests

import org.junit.jupiter.api.Test
import ru.tinkoff.fintech.lesson4.Stack
import kotlin.test.assertEquals

class StackTest {

    @Test
    fun `test stack for push` () {
        val stack = Stack<Int>()
        val ret = stack.push(3)
        assertEquals(true, ret)
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

        val size = stack.size()
        assertEquals(5, size)
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
            val element = stack.pop()
            assertEquals(el, element)
        }
    }

    @Test
    fun `test stack for pop` () {
        val stack = Stack<Int>()
        stack.push(3)
        val element = stack.pop()
        assertEquals(3, element)
    }

    @Test
    fun `test stack for pop Null` () {
        val stack = Stack<Int>()
        val element = stack.pop()
        assertEquals(null, element)
    }

    @Test
    fun `test stack for peek` () {
        val stack = Stack<Int>()
        stack.push(3)
        val element = stack.peek()
        assertEquals(3, element)
    }

    @Test
    fun `test stack for peek Null` () {
        val stack = Stack<Int>()
        val element = stack.peek()
        assertEquals(null, element)
    }

}