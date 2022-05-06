package ru.tinkoff.fintech.lesson9.testRepositories

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.tinkoff.fintech.lesson9.configuration.JpaStudentRepo
import ru.tinkoff.fintech.lesson9.repository.JpaStudentRepositoryImpl
import ru.tinkoff.fintech.lesson9.repository.StudentRepository
import ru.tinkoff.fintech.lesson9.student.StudentController
import ru.tinkoff.fintech.lesson9.student.model.StudentInfo
import ru.tinkoff.fintech.lesson9.student.service.StudentService
import java.lang.Thread.sleep


@SpringBootTest
@AutoConfigureWebTestClient
@Transactional
class CoroutinesTest {


    @Autowired
    lateinit var controller: StudentController

//    @MockkBean
//    private lateinit var studentRepository: StudentRepository

    lateinit var client: WebTestClient

    @BeforeEach
    fun setUp() {
        client = WebTestClient.bindToController(controller)
            .configureClient()
            .baseUrl("/university")
            .build()
    }


    @Test
    @ExperimentalCoroutinesApi
    fun `get one student`() {

        val result = client.get().uri("/get-student/1")
            .exchange()
            .expectStatus().isOk
            .expectBody<StudentInfo>()
            .returnResult()
        sleep(1000)
        assertEquals(result.responseBody, StudentInfo(1, "wat"))
    }

//    @Test
//    fun `post one student`() {
//        val result = client.post().uri("/add-new-student")
//            .bodyValue(StudentInfo(6, "master"))
//            .exchange()
//            .expectBody<String>()
//            .returnResult()
//
//        assertEquals(result.responseBody, "Got data")
//    }
//
//    @Test
//    fun `post and get one student`() {
//        val result = client.post().uri("/add-new-student")
//            .bodyValue(StudentInfo(1, "master"))
//            .exchange()
//
//        sleep(2000)
//        val result2 = client.get().uri("/get-student/1")
//            .exchange()
//            .expectStatus().isOk
//            .expectBody<StudentInfo>()
//            .returnResult()
//
//        assertEquals(result2.responseBody, StudentInfo(1, "master"))
//    }
//
//    @Test
//    fun `add new student`() {
//        val newStudent = StudentInfo(
//            5,
//            "master",
//            "Tim", "Timov",
//            4.2F,
//            "B",
//            "Good"
//        )
//
//        val addedStudent  = studentRepository.save(newStudent)
//        val student = studentRepository.findById(5)
//
//        assertEquals(student.get(), newStudent)
//    }
//
//
//    @Test
//    fun `find students with default val`() {
//        val students = studentRepository.search4Students()
//
//        assertEquals(bachelorStudents, students)
//    }
//
//    @Test
//    fun `find students`() {
//        val students = studentRepository.search4Students("master")
//
//        assertEquals(masterStudents, students)
//    }
//
//    private val listOfStudents = mutableListOf(
//        StudentInfo(
//            1,
//            "bachelor",
//            "Ivan", "Ivanov",
//            4.5F,
//            "B",
//            "Good"
//        ),
//        StudentInfo(
//            2,
//            "master",
//            "Petr", "Petrov",
//            3.5F,
//            "E",
//            "Not Good"
//        ),
//        StudentInfo(
//            3,
//            "bachelor",
//            "Igor", "Ivanov",
//            5F,
//            "A",
//            "Excellent"
//        ),
//        StudentInfo(
//            4,
//            "master",
//            "Igor", "Ivanov",
//            4.6F,
//            "B",
//            "Good"
//        )
//    )
//
//    private val masterStudents = mutableListOf(
//        StudentInfo(
//            2,
//            "master",
//            "Petr", "Petrov",
//            3.5F,
//            "E",
//            "Not Good"
//        ),
//        StudentInfo(
//            4,
//            "master",
//            "Igor", "Ivanov",
//            4.6F,
//            "B",
//            "Good"
//        )
//    )
//
//    private val bachelorStudents = mutableListOf(
//        StudentInfo(
//            1,
//            "bachelor",
//            "Ivan", "Ivanov",
//            4.5F,
//            "B",
//            "Good"
//        ),
//        StudentInfo(
//            3,
//            "bachelor",
//            "Igor", "Ivanov",
//            5F,
//            "A",
//            "Excellent"
//        )
//    )

}
