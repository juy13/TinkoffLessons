package ru.tinkoff.fintech.lesson9.student

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import ru.tinkoff.fintech.lesson9.student.model.StudentInfo
import ru.tinkoff.fintech.lesson9.student.service.StudentService
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono


@RestController
@RequestMapping("/university")
class StudentController(private val studentService: StudentService) {

    @GetMapping("/get-student/{studentId}")
    suspend fun getStudent(@PathVariable studentId: Int): Flux<StudentInfo> {
        val student = studentService.getStudent(studentId)
        return Flux.just(student)
    }


    @PostMapping("/add-new-student")
    fun addStudent(@RequestBody studentInfo: StudentInfo): String =
        studentService.newStudent(studentInfo)
}