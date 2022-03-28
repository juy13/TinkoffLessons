package ru.tinkoff.fintech.lesson4

class NoSuchElementException (message: String) : Exception(message)

class Queue<E> {

    private val INIT_SIZE = 5
    private var queue = arrayOfNulls<Any>(INIT_SIZE)
    private var queueSize = INIT_SIZE
    private var size = 0

    fun element() : E? {
        if (size == 0)
            throw NoSuchElementException("queue is empty")
        return queue[0] as E?
    }

    fun remove(): E? {
        if (size == 0)
            throw NoSuchElementException("queue is empty")
        return getElement()
    }

    fun peek(): E? {
        if (size == 0)
            return null
        return queue[0] as E?
    }

    fun poll(): E? {
        if (size == 0)
            return null
        return getElement()
    }

    private fun getElement() : E? {
        val retVal = queue[0]
        val newStack = arrayOfNulls<Any>(queueSize)
        queue.copyInto(newStack, 0, 1, size)
        queue = newStack
        size -= 1
        return retVal as E?
    }

    fun offer(obj : E) : Boolean {
        if (size < queueSize) {
            queue[size] = obj
            size++
            return true
        }
        if (size == queueSize) {
            resize(queueSize * 2)
            queue[size] = obj
            size++
            return true
        }
        return false
    }

    private fun resize(lengthOfNewArray : Int) {
        val newStack = arrayOfNulls<Any>(lengthOfNewArray)
        queue.copyInto(newStack, 0, 0, size)
        queue = newStack
        queueSize *= 2
    }

    fun size() : Int {
        return size
    }

}