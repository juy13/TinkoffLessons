package ru.tinkoff.fintech.lesson4

class Stack<E> {

    private val INIT_SIZE = 5
    private var stack = arrayOfNulls<Any>(INIT_SIZE)
    private var stackSize = INIT_SIZE
    private var pointer = 0

    fun push(obj : E) : Boolean {
        if (pointer < stackSize) {
            stack[pointer] = obj
            pointer++
            return true
        }
        if (pointer == stackSize) {
            resize(stackSize * 2)
            stack[pointer] = obj
            pointer++
            return true
        }
        return false
    }

    fun pop() : Any? {
        if ( pointer == 0 )
            return null
        pointer -= 1
        return stack[pointer]
    }

    fun peek() : Any? {
        if ( pointer == 0 )
            return null
        return stack[pointer - 1]
    }

    private fun resize(lengthOfNewArray : Int) {
        val newStack = arrayOfNulls<Any>(lengthOfNewArray)
        stack.copyInto(newStack, 0, 0, pointer)
        stack = newStack
        stackSize *= 2
    }

    fun size() : Int {
        return pointer
    }

}