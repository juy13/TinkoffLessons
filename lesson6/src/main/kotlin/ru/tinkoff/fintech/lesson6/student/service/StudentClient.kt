package ru.tinkoff.fintech.lesson6.student.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.*
import ru.tinkoff.fintech.lesson6.configuration.StudentNotFoundException
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

@Service
class StudentClient(
    private val restTemplate: RestTemplate,
    @Value("\${student.information}") private val studentInformation: String
) {
    fun getStudents(): List<StudentInfo> {
        return restTemplate.exchange<Set<StudentInfo>>("$studentInformation$GET_STUDENTS", HttpMethod.GET).body.orEmpty().toList()
    }


    @ExceptionHandler(StudentNotFoundException::class)
    fun getStudent(studentId: Int): StudentInfo {
        return restTemplate.getForObject("$studentInformation$GET_STUDENT_BY_ID", studentId)
    }


    fun newStudent(student: StudentInfo): StudentInfo =
        restTemplate.postForObject("$studentInformation$ADD_STUDENT", student)

    @ExceptionHandler(StudentNotFoundException::class)
    fun search4Students(degree: String): List<StudentInfo> {
        return restTemplate.postForObject("$studentInformation$SEARCH_STUDENT", degree)
    }
}

private const val GET_STUDENTS = "/university/students"
private const val GET_STUDENT_BY_ID = "/university/student/{studentId}"
private const val ADD_STUDENT = "/university/add"
private const val SEARCH_STUDENT = "/university/search"
