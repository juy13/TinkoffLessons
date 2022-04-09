package ru.tinkoff.fintech.lesson6.student.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson6.student.model.FullName
import ru.tinkoff.fintech.lesson6.student.model.Student
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

@Service
class StudentService (private val studentClient: StudentClient) {

    fun getStudents() : ResponseEntity<MutableList<StudentInfo?>> {
        return studentClient.getStudents()
    }

//    fun getStudent(studentId: Int): StudentInfo? {
//        require(studentId >= 0)
//        return studentClient.getStudent(studentId)
//    }
//
//    fun newStudent(fullName: FullName): StudentInfo
//    {
//        return studentClient.newStudent(fullName)
//    }
//
//    fun search4StudentId(firstName: String, secondName: String, degree: String): Set<StudentInfo> {
//        require(firstName.isNotEmpty() && secondName.isNotEmpty() && degree.isNotEmpty())
//        return studentClient.search4StudentId(firstName, secondName, degree)
//    }

}