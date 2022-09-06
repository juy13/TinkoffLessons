package ru.tinkoff.fintech.lesson6.student.model

data class StudentInfo(
    val id : Int,
    val degree : String,
    val fullName: FullName,
    val middleMark : Float,
    val mark : String,
    val comment : String
)