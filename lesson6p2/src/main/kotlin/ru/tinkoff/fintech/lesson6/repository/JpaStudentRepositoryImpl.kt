package ru.tinkoff.fintech.lesson6.repository

import org.springframework.stereotype.Service
import org.springframework.util.ObjectUtils
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

//@Primary
@Service
class JpaStudentRepositoryImpl (private val jpaStudentRepository: JpaStudentRepo) : StudentRepository {

    override fun getStudents(): List<StudentInfo> =
        jpaStudentRepository.findAll()

    override fun getStudent(studentId: Int): StudentInfo {
        val student = jpaStudentRepository.findById(studentId)
        if (student.isPresent) {
            return student.get()
        }
        return StudentInfo(-1,
            "unknown",
            "unknown",
            "unknown",
            -1F, "unknown",
            "unknown")
    }

    override fun newStudent(studentInfo: StudentInfo): StudentInfo {
        val student = jpaStudentRepository.save(studentInfo)
        if (ObjectUtils.isEmpty(student)) {
            return StudentInfo(-1,
                "unknown",
                "unknown",
                "unknown",
                -1F, "unknown",
                "unknown")
        }
        return student
    }

    override fun search4Students(degree: String): List<StudentInfo> {
        val students = jpaStudentRepository.search4Students(degree)
        if (students.isEmpty()) {
            return listOf(StudentInfo(-1,
                "unknown",
                "unknown",
                "unknown",
                -1F, "unknown",
                "unknown"))
        }
        return students
    }


}