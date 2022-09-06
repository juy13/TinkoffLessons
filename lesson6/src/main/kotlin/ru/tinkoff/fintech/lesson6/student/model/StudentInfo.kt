package ru.tinkoff.fintech.lesson6.student.model

data class StudentInfo(
    val id: Int = 0,
    val degree: String = "unknown",
    val firstName: String  = "unknown",
    val lastName: String  = "unknown",
    val middleMark: Float = 0F,
    val mark: String  = "unknown",
    val comment: String  = "unknown"
)
