package ru.tinkoff.fintech.lesson6.student

import org.springframework.web.bind.annotation.*
import ru.tinkoff.fintech.lesson6.student.model.FullName
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo
import ru.tinkoff.fintech.lesson6.student.service.StudentService


@RestController
@RequestMapping("/university")
class StudentController(private val studentService: StudentService) {

    @GetMapping("/students")
    fun getStudentList(): Set<StudentInfo> =
        studentService.getStudents()

    @GetMapping("/student/{studentId}")
    fun getStudent(@PathVariable studentId: Int): StudentInfo? =
        studentService.getStudent(studentId)


    @PostMapping("/add")
    fun addStudent(@RequestBody fullName: FullName): StudentInfo =
        studentService.newStudent(fullName)

    @GetMapping("/search")
    fun search4StudentId(
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestParam(defaultValue = "bachelor") degree: String
    ): Set<StudentInfo> = studentService.search4StudentId(firstName, lastName, degree)


}