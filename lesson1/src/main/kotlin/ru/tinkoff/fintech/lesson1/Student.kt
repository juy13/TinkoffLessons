package ru.tinkoff.fintech.lesson1

import java.math.BigDecimal

abstract class Student(val firstName: String, val lastName: String) {

    private var middleMark: Float = 0F
    private val listOfMarks: MutableList<Int> = mutableListOf()
    abstract var scholarship: BigDecimal

    fun printName() = println("First name : $firstName, Last name : $lastName")

    abstract fun getYearsOfStudy(): String
    fun setMark(mark: Int) {
        listOfMarks.add(mark)
        middleMark = (listOfMarks.sum().toFloat() / listOfMarks.size.toFloat())
    }

    abstract fun countScholarship(): BigDecimal

    fun getMiddleMark() = middleMark

}