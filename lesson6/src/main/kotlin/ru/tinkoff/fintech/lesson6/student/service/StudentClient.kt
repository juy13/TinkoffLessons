package ru.tinkoff.fintech.lesson6.student.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.client.*
import ru.tinkoff.fintech.lesson6.student.model.FullName
import ru.tinkoff.fintech.lesson6.student.model.Student
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

@Service
class StudentClient(
    private val restTemplate: RestTemplate,
    @Value("\${student.information}") private val studentInformation: String
) {
    fun getStudents(): Set<StudentInfo> {
        return restTemplate.exchange<Set<StudentInfo>>("$studentInformation$GET_STUDENTS", HttpMethod.GET).body.orEmpty()
    }

    fun getStudent(studentId: Int): StudentInfo? = try {
        restTemplate.getForObject("$studentInformation$GET_STUDENT_BY_ID", studentId)
    } catch (e: HttpClientErrorException.NotFound) {
        null
    }

    fun newStudent(student: FullName): StudentInfo =
        restTemplate.postForObject("$studentInformation$ADD_STUDENT", student)

    fun search4StudentId(firstName: String, secondName: String, degree: String): Set<StudentInfo> =
        restTemplate.postForObject("$studentInformation$SEARCH_STUDENT", firstName, secondName, degree)
}

private const val GET_STUDENTS = "/students"
private const val GET_STUDENT_BY_ID = "/student/{studentId}"
private const val ADD_STUDENT = "/add"
private const val SEARCH_STUDENT = "/search"
