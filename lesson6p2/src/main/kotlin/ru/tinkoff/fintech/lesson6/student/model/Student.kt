package ru.tinkoff.fintech.lesson6.student.model
import java.math.BigDecimal

abstract class Student(val firstName: String, val lastName: String, val id : Int, val degree : String) {

    private var middleMark: Float = 0F
    private val listOfMarks: MutableList<Int> = mutableListOf()
    abstract var scholarship: BigDecimal

    fun getName() : FullName = FullName(firstName, lastName)

    abstract fun getYearsOfStudy(): String

    fun setMark(mark: Int) {
        listOfMarks.add(mark)
        middleMark = (listOfMarks.sum().toFloat() / listOfMarks.size.toFloat())
    }

    abstract fun countScholarship(): BigDecimal

    fun getMiddleMark() = middleMark

}