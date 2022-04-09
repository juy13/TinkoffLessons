package ru.tinkoff.fintech.lesson6

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.util.AssertionErrors.assertEquals
import org.springframework.util.Assert
import ru.tinkoff.fintech.lesson6.repository.StudentRepository
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo
import javax.sql.DataSource


//@SpringBootTest
//@AutoConfigureMockMvc
//private val mockMvc: MockMvc,
@DataJpaTest
//@Sql(scripts = ["/data.sql"])
class StudentControllerTest   {

	@Autowired
	private lateinit var ds : DataSource

	@Autowired
	private val studentRepository: StudentRepository? = null

//INSERT INTO STUDENT_INFO VALUES(0, 'Good', 'bachelor', 'Ivan', 'Ivanov',  'B', 4.5);
//INSERT INTO STUDENT_INFO VALUES(1,'Not Good','master','Petr', 'Petrov','E',3.5);
//INSERT INTO STUDENT_INFO VALUES(2,'Excellent','bachelor','Igor', 'Ivanov','A',5);
//INSERT INTO STUDENT_INFO VALUES(3,'Good','master','Igor', 'Ivanov','B',4.6);

//	@MockkBean
//	private lateinit var studentClient: StudentClient

//	@BeforeEach
//	private fun initMock() {
////		every { studentClient.getStudents() } returns studentRepository.findAll()
////		every { studentClient.getStudent(any()) } answers { listOfStudents.find { it.id == firstArg() } }
////		every { studentClient.newStudent(any()) } answers {
////			val index = listOfStudents.last().id
////			val studentInfo = StudentInfo(
////				index + 1,
////				"unknown",
////				firstArg(),
////				0F,
////				"unknown",
////				"unknown"
////			)
////			listOfStudents.add(studentInfo)
////			studentInfo
////		}
////		every { studentClient.search4StudentId(any(), any(), any()) } answers {
////			val fullName = FullName(firstArg<String>(), secondArg<String>())
////			listOfStudents.filter { (fullName in it.fullName) && (it.degree == thirdArg()) }.toSet()
////		}
//	}


//	@AfterEach
//	private fun clearMock() {
//		clearAllMocks()
//	}

	@Test
	fun `get list of students`() {
		val students = studentRepository?.findAll()
		students?.get(0)?.let { println(it.id) }

		assertEquals(listOfStudents, students)
	}

	fun<T> isEqual(first: List<T>, second: List<T>): Boolean {

		if (first.size != second.size) {
			return false
		}

		first.forEachIndexed { index, value -> if (second[index] != value) { return false} }
		return true
	}

//	@Test
//	fun `get one student`() {
//		val student : StudentInfo = getOneStudent(1).readResponse()
//		println(student)
//		Assertions.assertEquals(StudentInfo(
//			1,
//			"master",
//			"Petr", "Petrov",
//			3.5F,
//			"E",
//			"Not Good"
//		), student)
//	}
//
//	@Test
//	fun `add new student`() {
//		val newStudent = FullName("Tigran", "Tigranov")
//
//		val addedStudent: StudentInfo = addStudent(newStudent).readResponse()
//		val listOfStudents = findStudent(newStudent.firstName, newStudent.lastName, "unknown").readResponse<Set<StudentInfo>>()
//
//		Assertions.assertEquals(setOf(addedStudent), listOfStudents)
//	}
//
//	@Test
//	fun `find a student with default val`() {
//		val newStudent = FullName("Igor", "Ivanov")
//
//		val listOfStudents = findStudent(newStudent.firstName, newStudent.lastName).readResponse<Set<StudentInfo>>()
//
//		Assertions.assertEquals(
//			setOf(
//				StudentInfo(
//					2,
//					"bachelor",
//					"Igor", "Ivanov",
//					5F,
//					"A",
//					"Excellent"
//				)
//			),
//			listOfStudents
//		)
//	}

//	@Test
//	fun `find a student`() {
//		val newStudent = FullName("Petr", "Petrov")
//
//		val listOfStudents = findStudent(newStudent.firstName, newStudent.lastName, "master").readResponse<Set<StudentInfo>>()
//
//		Assertions.assertEquals(
//			setOf(
//				StudentInfo(
//					1,
//					"master",
//					"Petr", "Petrov",
//					3.5F,
//					"E",
//					"Not Good"
//				)
//			),
//			listOfStudents
//		)
//	}

//	@Test
//	fun `find a students`() {
//		val newStudent = FullName("Igor", "Ivanov")
//
//		val listOfStudents = findStudent(newStudent.firstName, newStudent.lastName).readResponse<Set<StudentInfo>>()
//
//		Assertions.assertEquals(
//			setOf(
//				StudentInfo(
//					2,
//					"bachelor",
//					"Igor", "Ivanov",
//					5F,
//					"A",
//					"Excellent"
//				)
//			),
//			listOfStudents
//		)
//	}

//	private fun findStudent(firstName: String, lastName: String, degree: String? = null): ResultActions =
//		mockMvc.perform(
//			get("/university/search")
//				.params(createParams("firstName" to firstName, "lastName" to lastName, "degree" to degree))
//		)
//
//	private fun addStudent(newStudent: FullName): ResultActions =
//		mockMvc.perform(
//			post("/university/add")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(newStudent))
//		)
//
//	private fun getStudentList(): Set<StudentInfo> =
//		mockMvc.get("/university/students").readResponse()
//
//	private fun getOneStudent(studentId: Int): ResultActions =
//		mockMvc.perform(get("/university/student/$studentId"))

//	private inline fun <reified T> ResultActionsDsl.readResponse(expectedStatus: HttpStatus = HttpStatus.OK): T = this
//		.andExpect { status { isEqualTo(expectedStatus.value()) } }
//		.andReturn().response.getContentAsString(Charsets.UTF_8)
//		.let { if (T::class == String::class) it as T else objectMapper.readValue(it) }
//
//	private inline fun <reified T> ResultActions.readResponse(): T = this
//		.andExpect(MockMvcResultMatchers.status().isOk)
//		.andReturn().response.getContentAsString(Charsets.UTF_8)
//		.let { if (T::class == String::class) it as T else objectMapper.readValue(it) }
//
//	private fun createParams(vararg params: Pair<String, Any?>) =
//		LinkedMultiValueMap<String, String>().apply {
//			params.forEach { (key, value) -> if (value != null) add(key, value.toString()) }
//		}

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

}
