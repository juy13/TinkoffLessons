package ru.tinkoff.fintech.lesson6.repository

import ru.tinkoff.fintech.lesson6.student.model.StudentInfo



interface StudentRepository {

    fun getStudents() : List<StudentInfo>

    fun getStudent(studentId: Int): StudentInfo

    fun newStudent(studentInfo: StudentInfo) : StudentInfo

    fun search4Students(degree : String = "bachelor") : List<StudentInfo>
}