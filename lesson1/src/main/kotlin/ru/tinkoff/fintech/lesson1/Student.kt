package ru.tinkoff.fintech.lesson1

abstract class Student (val firstName : String, val lastName : String) {

    private var middleMark : Float = 0F
    private val listOfMarks : MutableList<Int> = mutableListOf()
    abstract var scholarship: Int

    fun printName() = println("First name : $firstName, Last name : $lastName")

    abstract fun getYearsOfStudy () : String
    fun setMark (mark : Int) {
        listOfMarks.add(mark)
        middleMark = (listOfMarks.sum().toFloat() / listOfMarks.size.toFloat())
    }

    abstract fun countScholarship() : Float

    fun getMiddleMark() = middleMark

}