package ru.tinkoff.fintech.lesson6.student.service

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson6.repository.StudentRepository
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

@Service
class StudentService (private val studentRepository: StudentRepository) {

    fun getStudents() : List<StudentInfo> {
        return studentRepository.getStudents()
    }

    fun getStudent(studentId: Int): StudentInfo {
        require(studentId >= 0)
        return studentRepository.getStudent(studentId)
    }

    fun newStudent(studentInfo: StudentInfo): StudentInfo
    {
        return studentRepository.newStudent(studentInfo)
    }

    fun search4Students(degree: String): List<StudentInfo> {
        require(degree.isNotEmpty())
        return studentRepository.search4Students(degree)
    }

}