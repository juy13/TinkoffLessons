package ru.tinkoff.fintech.lesson6.testRepositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import ru.tinkoff.fintech.lesson6.repository.JpaStudentRepo
import ru.tinkoff.fintech.lesson6.repository.JpaStudentRepositoryImpl
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo


@DataJpaTest
class JpaStudentControllerTest {


    @Autowired
    private lateinit var studentRepository: JpaStudentRepo

    @Test
    fun `get list of students`() {
        val students = studentRepository.let { JpaStudentRepositoryImpl(it).getStudents() }

        assertEquals(listOfStudents, students)
    }

    @Test
    fun `get one student`() {
        val student = studentRepository.findById(1)
        println(student)
        assertEquals(
            StudentInfo(
                1,
                "bachelor",
                "Ivan", "Ivanov",
                4.5F,
                "B",
                "Good"
            ), student.get()
        )
    }

    @Test
    fun `add new student`() {
        val newStudent = StudentInfo(
            5,
            "master",
            "Tim", "Timov",
            4.2F,
            "B",
            "Good"
        )

        val addedStudent  = studentRepository.save(newStudent)
        val student = studentRepository.findById(5)

        assertEquals(student.get(), newStudent)
    }


    @Test
    fun `find students with default val`() {
        val students = studentRepository.search4Students()

        assertEquals(bachelorStudents, students)
    }

    @Test
    fun `find students`() {
        val students = studentRepository.search4Students("master")

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
