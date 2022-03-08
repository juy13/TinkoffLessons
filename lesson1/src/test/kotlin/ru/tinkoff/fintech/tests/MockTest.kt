package ru.tinkoff.fintech.tests

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.tinkoff.fintech.lesson1.InfoStudent
import ru.tinkoff.fintech.lesson1.MasteryStudent


@ExtendWith(MockKExtension::class)
class MockTest {

    @MockK
    lateinit var masteryStudent: MasteryStudent

    @BeforeEach
    fun init() {
        every { masteryStudent.firstName } returns "Dima"
        every { masteryStudent.lastName } returns "Chao"
    }

    @Test
    fun `test Mastery B student with mock`() {
        every { masteryStudent.getMiddleMark() } returns 4.55F

        val infoStudent = InfoStudent(masteryStudent)

        val output = tapSystemOut {
            infoStudent.getInfoStudent()
        }

        assertEquals("Student Chao Dima has a middle mark : 4.55 B student", output.trim())
        verify(exactly = 5) { masteryStudent.getMiddleMark()  }
    }

    @Test
    fun `test Mastery C student with mock`() {
        every { masteryStudent.getMiddleMark() } returns 3.55F

        val infoStudent = InfoStudent(masteryStudent)

        val output = tapSystemOut {
            infoStudent.getInfoStudent()
        }

        assertEquals("Student Chao Dima has a middle mark : 3.55 C student", output.trim())
        verify(exactly = 1) { masteryStudent.lastName }
    }

    @Test
    fun `test Mastery A student with mock`() {
        every { masteryStudent.getMiddleMark() } returns 5F

        val infoStudent = InfoStudent(masteryStudent)

        val output = tapSystemOut {
            infoStudent.getInfoStudent()
        }

        assertEquals("Student Chao Dima has a middle mark : 5.0 A student", output.trim())
        verify(exactly = 1) { masteryStudent.firstName }
    }

    @Test
    fun `test Mastery a bad student with mock`() {
        every { masteryStudent.getMiddleMark() } returns 2.66F

        val infoStudent = InfoStudent(masteryStudent)

        val output = tapSystemOut {
            infoStudent.getInfoStudent()
        }

        assertEquals("Student Chao Dima has a middle mark : 2.66 too bad", output.trim())
        verify(exactly = 1) { masteryStudent.firstName }
    }

}