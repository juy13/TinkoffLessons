package ru.tinkoff.fintech.lesson9.testRepositories

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Flux
import ru.tinkoff.fintech.lesson9.student.StudentController
import ru.tinkoff.fintech.lesson9.student.model.StudentInfo

@WebFluxTest
class CoroutinesTest {


    @MockkBean
    lateinit var controller: StudentController

    @Autowired
    lateinit var client: WebTestClient

    @BeforeEach
    fun setUp() {
        client = WebTestClient.bindToController(controller)
            .configureClient()
            .baseUrl("/university")
            .build()
    }

    @Test
    @DisplayName("Getting all languages should return 200 with the list of all languages from the service")
    fun `get student with id 1`() {

        every { controller.getStudent(1) } returns Flux.just(StudentInfo(1, "qwerty"))

        val result = client
            .get()
            .uri("/get-student/1")
            .exchange()
            .expectStatus().isOk
            .expectBody<String>()
            .returnResult()

        assertEquals("[{\"id\":1,\"logging\":\"qwerty\"}]", result.responseBody)
    }

    @Test
    fun `post one student`() {

        every { controller.addStudent(StudentInfo(6, "master")) } returns "Got data"

        val result = client.post().uri("/add-new-student")
            .bodyValue(StudentInfo(6, "master"))
            .exchange()
            .expectBody<String>()
            .returnResult()

        assertEquals(result.responseBody, "Got data")
    }

}
