package ru.tinkoff.fintech.lesson6.student.service

import org.apache.commons.lang3.ObjectUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import ru.tinkoff.fintech.lesson6.repository.StudentRepository
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

@Service
class StudentClient(
    @Value("\${student.information}") private val studentInformation: String,
    private val studentRepository: StudentRepository
) {
    fun getStudents(): ResponseEntity<MutableList<StudentInfo?>> {
        val gadgets = studentRepository.findAll()
        if (gadgets.isEmpty()) {
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity(gadgets, HttpStatus.OK)
    }

    fun getStudent(studentId: Int): ResponseEntity<StudentInfo> {
        val gadget = studentRepository.findById(studentId)
        if (gadget.isPresent) {
            return ResponseEntity<StudentInfo>(gadget.get(), HttpStatus.OK)
        }
        return ResponseEntity<StudentInfo>(HttpStatus.NOT_FOUND)
    }

    fun newStudent(studentInfo: StudentInfo, uri: UriComponentsBuilder): ResponseEntity<StudentInfo> {
        val student = studentRepository.save(studentInfo)
        if (ObjectUtils.isEmpty(student)) {
            return ResponseEntity<StudentInfo>(HttpStatus.BAD_REQUEST)
        }
        val headers = HttpHeaders()
        headers.location = uri.path("/student/{studentId}").buildAndExpand(student.id).toUri();
        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    fun search4Student(firstName: String, secondName: String, degree: String): ResponseEntity<StudentInfo> {
        val student = studentRepository.findByNameAndDegree(firstName, secondName, degree)
        if (student != null) {
            return ResponseEntity<StudentInfo>(student, HttpStatus.OK)
        }
        return ResponseEntity<StudentInfo>(HttpStatus.NOT_FOUND)
    }
}

private const val GET_STUDENTS = "/students"
private const val GET_STUDENT_BY_ID = "/student/{studentId}"
private const val ADD_STUDENT = "/add"
private const val SEARCH_STUDENT = "/search"
