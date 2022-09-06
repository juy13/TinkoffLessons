package ru.tinkoff.fintech.lesson6.student

import org.springframework.web.bind.annotation.*
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo
import ru.tinkoff.fintech.lesson6.student.service.StudentService


@RestController
@RequestMapping("/university")
class StudentController(private val studentService: StudentService) {

    @GetMapping("/students")
    fun getStudentList(): List<StudentInfo> =
        studentService.getStudents()

    @GetMapping("/student/{studentId}")
    fun getStudent(@PathVariable studentId: Int): StudentInfo =
        studentService.getStudent(studentId)

    @PostMapping("/add")
    fun addStudent(@RequestBody studentInfo: StudentInfo): StudentInfo =
        studentService.newStudent(studentInfo)

    @GetMapping("/search")
    fun search4Students(
        @RequestParam(defaultValue = "bachelor") degree: String,
        @RequestParam(defaultValue = "1") pageNo : Int,
        @RequestParam(defaultValue = "2") pageSize : Int
    ): List<StudentInfo> = studentService.search4Students(degree, pageNo, pageSize)


}