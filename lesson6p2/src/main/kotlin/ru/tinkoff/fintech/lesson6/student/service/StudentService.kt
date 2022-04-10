package ru.tinkoff.fintech.lesson6.student.service

import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import ru.tinkoff.fintech.lesson6.student.model.FullName
import ru.tinkoff.fintech.lesson6.student.model.Student
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

@Service
class StudentService (private val studentClient: StudentClient) {

    fun getStudents() : ResponseEntity<MutableList<StudentInfo?>> {
        return studentClient.getStudents()
    }

    fun getStudent(studentId: Int): ResponseEntity<StudentInfo> {
        require(studentId >= 0)
        return studentClient.getStudent(studentId)
    }

    fun newStudent(studentInfo: StudentInfo, uri: UriComponentsBuilder): ResponseEntity<StudentInfo>
    {
        return studentClient.newStudent(studentInfo, uri)
    }

    fun search4Student(firstName: String, secondName: String, degree: String): ResponseEntity<StudentInfo> {
        require(firstName.isNotEmpty() && secondName.isNotEmpty() && degree.isNotEmpty())
        return studentClient.search4Student(firstName, secondName, degree)
    }

}