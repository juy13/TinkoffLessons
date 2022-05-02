package ru.tinkoff.fintech.lesson6.testRepositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate
import ru.tinkoff.fintech.lesson6.repository.JdbcStudentRepositoryImpl
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo
import javax.sql.DataSource


@JdbcTest
class JdbcStudentRepositoryTest {

    @Autowired
    private lateinit var ds: DataSource

    @Test
    fun `get list of students`() {
        val students = JdbcStudentRepositoryImpl(JdbcTemplate(ds)).getStudents()

        assertEquals(listOfStudents, students)
    }

    @Test
    fun `get one student`() {
        val student = JdbcStudentRepositoryImpl(JdbcTemplate(ds)).getStudent(1)

        assertEquals(
            StudentInfo(
                1,
                "bachelor",
                "Ivan", "Ivanov",
                4.5F,
                "B",
                "Good"
            ), student
        )
    }

    @Test
    fun `add new student`() {
        val student = StudentInfo(
            5,
            "master",
            "Tim", "Timov",
            4.2F,
            "B",
            "Good"
        )

        JdbcStudentRepositoryImpl(JdbcTemplate(ds)).newStudent(student)
        val addedStudent = JdbcStudentRepositoryImpl(JdbcTemplate(ds)).getStudent(5)

        assertEquals(student, addedStudent)
    }


    @Test
    fun `find students with default val`() {
        val students = JdbcStudentRepositoryImpl(JdbcTemplate(ds)).search4Students()

        assertEquals(bachelorStudents, students)
    }

    @Test
    fun `find students`() {
        val students = JdbcStudentRepositoryImpl(JdbcTemplate(ds)).search4Students("master")

        assertEquals(masterStudents, students)
    }

    private val listOfStudents = mutableListOf(
        StudentInfo(
            1,
            "bachelor",
            "Ivan", "Ivanov",
            4.5F,
            "B",
            "Good"
        ),
        StudentInfo(
            2,
            "master",
            "Petr", "Petrov",
            3.5F,
            "E",
            "Not Good"
        ),
        StudentInfo(
            3,
            "bachelor",
            "Igor", "Ivanov",
            5F,
            "A",
            "Excellent"
        ),
        StudentInfo(
            4,
            "master",
            "Igor", "Ivanov",
            4.6F,
            "B",
            "Good"
        )
    )

    private val masterStudents = mutableListOf(
        StudentInfo(
            2,
            "master",
            "Petr", "Petrov",
            3.5F,
            "E",
            "Not Good"
        ),
        StudentInfo(
            4,
            "master",
            "Igor", "Ivanov",
            4.6F,
            "B",
            "Good"
        )
    )

    private val bachelorStudents = mutableListOf(
        StudentInfo(
            1,
            "bachelor",
            "Ivan", "Ivanov",
            4.5F,
            "B",
            "Good"
        ),
        StudentInfo(
            3,
            "bachelor",
            "Igor", "Ivanov",
            5F,
            "A",
            "Excellent"
        )
    )

}
