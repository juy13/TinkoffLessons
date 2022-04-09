package ru.tinkoff.fintech.lesson6.student

import org.springframework.http.ResponseEntity
import org.springframework.test.context.jdbc.Sql
import org.springframework.web.bind.annotation.*
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo
import ru.tinkoff.fintech.lesson6.student.service.StudentService


@RestController
@RequestMapping("/university")
@Sql(scripts = ["/data.sql"])
class StudentController(private val studentService: StudentService) {

    @GetMapping("/students")
    fun getStudentList(): ResponseEntity<MutableList<StudentInfo?>> =
        studentService.getStudents()

//    @GetMapping("/student/{studentId}")
//    fun getStudent(@PathVariable studentId: Int): StudentInfo? =
//        studentService.getStudent(studentId)
//
//
//    @PostMapping("/add")
//    fun addStudent(@RequestBody fullName: FullName): StudentInfo =
//        studentService.newStudent(fullName)
//
//    @GetMapping("/search")
//    fun search4StudentId(
//        @RequestParam firstName: String,
//        @RequestParam lastName: String,
//        @RequestParam(defaultValue = "bachelor") degree: String
//    ): Set<StudentInfo> = studentService.search4StudentId(firstName, lastName, degree)


}