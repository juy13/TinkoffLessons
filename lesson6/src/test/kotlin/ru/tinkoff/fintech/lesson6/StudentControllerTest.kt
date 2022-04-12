package ru.tinkoff.fintech.lesson6

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ninjasquad.springmockk.MockkBean
import io.mockk.clearAllMocks
import io.mockk.every
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo
import ru.tinkoff.fintech.lesson6.student.service.StudentClient

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest @Autowired constructor(private val mockMvc: MockMvc, private val objectMapper: ObjectMapper)   {

	@MockkBean
	private lateinit var studentClient: StudentClient

	@BeforeEach
	private fun initMock() {
		every { studentClient.getStudents() } returns listOfStudents
		every { studentClient.getStudent(any()) } answers {
			listOfStudents.find { it.id == firstArg() }
				?: StudentInfo(
					-1,
					"unknown",
					"unknown",
					"unknown",
					-1F, "unknown",
					"unknown"
				)
		}
		every { studentClient.newStudent(any()) } answers {
			listOfStudents.add(firstArg())
			firstArg()
		}
		every { studentClient.search4Students(any())} answers {
			listOfStudents.filter { (it.degree == firstArg())}
		}
	}


	@AfterEach
	private fun clearMock() {
		clearAllMocks()
	}

	@Test
	fun `get list of students`() {
		val students = getStudentList()
		println(students)
		Assertions.assertEquals(listOfStudents, students)
	}

	@Test
	fun `get one student`() {
		val student : StudentInfo = getOneStudent(1).readResponse()
		println(student)
		Assertions.assertEquals(StudentInfo(
			1,
			"master",
			"Petr",
			"Petrov",
			3.5F,
			"E",
			"Not Good"
		), student)
	}

	@Test
	fun `add new student`() {
		val newStudent = StudentInfo(
			5,
			"master",
			"Tigran",
			"Tigranov",
			3.5F,
			"E",
			"Not Good"
		)

		val addedStudent: StudentInfo = addStudent(newStudent).readResponse()
		val student = getOneStudent(5).readResponse<StudentInfo>()

		Assertions.assertEquals(addedStudent, student)
	}

	@Test
	fun `find a student with default val`() {
		val listOfStudents = findStudent().readResponse<List<StudentInfo>>()

		Assertions.assertEquals(
			bachelorStudents,
			listOfStudents
		)
	}

	@Test
	fun `find a student`() {

		val listOfStudents = findStudent("master").readResponse<List<StudentInfo>>()

		Assertions.assertEquals(
			masterStudents,
			listOfStudents
		)
	}

	private fun findStudent(degree: String? = null): ResultActions =
		mockMvc.perform(
			get("/university/search")
				.params(createParams( "degree" to degree))
		)

	private fun addStudent(newStudent: StudentInfo): ResultActions =
		mockMvc.perform(
			post("/university/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newStudent))
		)

	private fun getStudentList(): List<StudentInfo> =
		mockMvc.get("/university/students").readResponse()

	private fun getOneStudent(studentId: Int): ResultActions =
		mockMvc.perform(get("/university/student/$studentId"))

	private inline fun <reified T> ResultActionsDsl.readResponse(expectedStatus: HttpStatus = HttpStatus.OK): T = this
		.andExpect { status { isEqualTo(expectedStatus.value()) } }
		.andReturn().response.getContentAsString(Charsets.UTF_8)
		.let { if (T::class == String::class) it as T else objectMapper.readValue(it) }

	private inline fun <reified T> ResultActions.readResponse(): T = this
		.andExpect(MockMvcResultMatchers.status().isOk)
		.andReturn().response.getContentAsString(Charsets.UTF_8)
		.let { if (T::class == String::class) it as T else objectMapper.readValue(it) }

	private fun createParams(vararg params: Pair<String, Any?>) =
		LinkedMultiValueMap<String, String>().apply {
			params.forEach { (key, value) -> if (value != null) add(key, value.toString()) }
		}

	private val listOfStudents = mutableListOf(
		StudentInfo(
			0,
			"bachelor",
			"Ivan",
			"Ivanov",
			4.5F,
			"B",
			"Good"
		),
		StudentInfo(
			1,
			"master",
			"Petr",
			"Petrov",
			3.5F,
			"E",
			"Not Good"
		),
		StudentInfo(
			2,
			"bachelor",
			"Igor",
			"Ivanov",
			5F,
			"A",
			"Excellent"
		),
		StudentInfo(
			3,
			"master",
			"Igor",
			"Ivanov",
			4.6F,
			"B",
			"Good"
		)
	)

	private val masterStudents = mutableListOf(
		StudentInfo(
			1,
			"master",
			"Petr", "Petrov",
			3.5F,
			"E",
			"Not Good"
		),
		StudentInfo(
			3,
			"master",
			"Igor", "Ivanov",
			4.6F,
			"B",
			"Good"
		)
	)

	private val bachelorStudents = mutableListOf(
		StudentInfo(
			0,
			"bachelor",
			"Ivan", "Ivanov",
			4.5F,
			"B",
			"Good"
		),
		StudentInfo(
			2,
			"bachelor",
			"Igor", "Ivanov",
			5F,
			"A",
			"Excellent"
		)
	)

}
