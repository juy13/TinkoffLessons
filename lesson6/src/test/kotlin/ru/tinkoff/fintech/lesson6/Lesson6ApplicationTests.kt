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
import ru.tinkoff.fintech.lesson6.student.model.FullName
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo
import ru.tinkoff.fintech.lesson6.student.service.StudentClient
import kotlin.random.Random

@SpringBootTest
@AutoConfigureMockMvc
class Lesson6ApplicationTests @Autowired constructor(private val mockMvc: MockMvc, private val objectMapper: ObjectMapper)   {

	@MockkBean
	private lateinit var studentClient: StudentClient

	@BeforeEach
	private fun initMock() {
		every { studentClient.getStudents() } returns listOfStudents.toSet()
		every { studentClient.getStudent(any()) } answers { listOfStudents.find { it.id == firstArg() } }
		every { studentClient.newStudent(any()) } answers {
			val index = listOfStudents.last().id
			val studentInfo = StudentInfo(
				index + 1,
				"unknown",
				firstArg(),
				0F,
				"unknown",
				"unknown"
			)
			listOfStudents.add(studentInfo)
			studentInfo
		}
		every { studentClient.search4StudentId(any(), any(), any()) } answers {
			val fullName = FullName(firstArg<String>(), secondArg<String>())
			listOfStudents.filter { (fullName in it.fullName) && (it.degree == thirdArg()) }.toSet()
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
		Assertions.assertEquals(listOfStudents.toSet(), students)
	}

	@Test
	fun `get one student`() {
		val student : StudentInfo = getOneStudent(1).readResponse()
		println(student)
		Assertions.assertEquals(StudentInfo(
			1,
			"master",
			FullName("Petr", "Petrov"),
			3.5F,
			"E",
			"Not Good"
		), student)
	}

	@Test
	fun `add new student`() {
		val newStudent = FullName("Tigran", "Tigranov")

		val addedStudent: StudentInfo = addStudent(newStudent).readResponse()
		val listOfStudents = findStudent(newStudent.firstName, newStudent.lastName, "unknown").readResponse<Set<StudentInfo>>()

		Assertions.assertEquals(setOf(addedStudent), listOfStudents)
	}

	@Test
	fun `find a student with default val`() {
		val newStudent = FullName("Igor", "Ivanov")

		val listOfStudents = findStudent(newStudent.firstName, newStudent.lastName).readResponse<Set<StudentInfo>>()

		Assertions.assertEquals(
			setOf(
				StudentInfo(
					2,
					"bachelor",
					FullName("Igor", "Ivanov"),
					5F,
					"A",
					"Excellent"
				)
			),
			listOfStudents
		)
	}

	@Test
	fun `find a student`() {
		val newStudent = FullName("Petr", "Petrov")

		val listOfStudents = findStudent(newStudent.firstName, newStudent.lastName, "master").readResponse<Set<StudentInfo>>()

		Assertions.assertEquals(
			setOf(
				StudentInfo(
					1,
					"master",
					FullName("Petr", "Petrov"),
					3.5F,
					"E",
					"Not Good"
				)
			),
			listOfStudents
		)
	}

	@Test
	fun `find a students`() {
		val newStudent = FullName("Igor", "Ivanov")

		val listOfStudents = findStudent(newStudent.firstName, newStudent.lastName).readResponse<Set<StudentInfo>>()

		Assertions.assertEquals(
			setOf(
				StudentInfo(
					2,
					"bachelor",
					FullName("Igor", "Ivanov"),
					5F,
					"A",
					"Excellent"
				)
			),
			listOfStudents
		)
	}

	private fun findStudent(firstName: String, lastName: String, degree: String? = null): ResultActions =
		mockMvc.perform(
			get("/university/search")
				.params(createParams("firstName" to firstName, "lastName" to lastName, "degree" to degree))
		)

	private fun addStudent(newStudent: FullName): ResultActions =
		mockMvc.perform(
			post("/university/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newStudent))
		)

	private fun getStudentList(): Set<StudentInfo> =
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
			FullName("Ivan", "Ivanov"),
			4.5F,
			"B",
			"Good"
		),
		StudentInfo(
			1,
			"master",
			FullName("Petr", "Petrov"),
			3.5F,
			"E",
			"Not Good"
		),
		StudentInfo(
			2,
			"bachelor",
			FullName("Igor", "Ivanov"),
			5F,
			"A",
			"Excellent"
		),
		StudentInfo(
			3,
			"master",
			FullName("Igor", "Ivanov"),
			4.6F,
			"B",
			"Good"
		)
	)

}
