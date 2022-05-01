package ru.tinkoff.fintech.lesson6.repository

import ru.tinkoff.fintech.lesson6.student.model.StudentInfo



interface StudentRepository {

    fun getStudent(studentId: Int): StudentInfo

    suspend fun newStudent(studentInfo: StudentInfo)
}