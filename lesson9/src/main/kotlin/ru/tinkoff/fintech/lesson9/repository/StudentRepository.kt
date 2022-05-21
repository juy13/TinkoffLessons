package ru.tinkoff.fintech.lesson9.repository

import ru.tinkoff.fintech.lesson9.student.model.StudentInfo


interface StudentRepository {

    fun getStudent(studentId: Int): StudentInfo

    suspend fun newStudent(studentInfo: StudentInfo)
}