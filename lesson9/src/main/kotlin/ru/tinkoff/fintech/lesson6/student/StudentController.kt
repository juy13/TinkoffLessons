package ru.tinkoff.fintech.lesson6.student

import org.springframework.web.bind.annotation.*
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo
import ru.tinkoff.fintech.lesson6.student.service.StudentService


@RestController
@RequestMapping("/university")
class StudentController(private val studentService: StudentService) {

    @GetMapping("/get-new-student/{studentId}")
    fun getStudent(@PathVariable studentId: Int): StudentInfo =
        studentService.getStudent(studentId)

    @PostMapping("/add-new-student")
    fun addStudent(@RequestBody studentInfo: StudentInfo): String =
        studentService.newStudent(studentInfo)
}