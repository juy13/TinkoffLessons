package ru.tinkoff.fintech.lesson6.student.service

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

@Service
class StudentService (private val studentClient: StudentClient) {

    fun getStudents() : List<StudentInfo> {
        return studentClient.getStudents()
    }

    fun getStudent(studentId: Int): StudentInfo {
        require(studentId >= 0)
        return studentClient.getStudent(studentId)
    }

    fun newStudent(studentInfo: StudentInfo): StudentInfo
    {
        return studentClient.newStudent(studentInfo)
    }

    fun search4Students(degree: String): List<StudentInfo> {
        require(degree.isNotEmpty())
        return studentClient.search4Students(degree)
    }

}