package ru.tinkoff.fintech.tests

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tinkoff.fintech.lesson1.MasteryStudent
import java.math.BigDecimal

class UnitTest {

    @Test
    fun `test scholarship for Mastery C student` () {
        val masteryStudent = MasteryStudent("Nikolai", "Osipov")
        masteryStudent.scholarship = BigDecimal(3000)
        for (i in 1..7) {
            masteryStudent.setMark(3)
        }
        assertEquals(BigDecimal.valueOf(3600.0), masteryStudent.countScholarship())
    }


    @Test
    fun `test scholarship for Mastery B student` () {
        val masteryStudent = MasteryStudent("Nikolai", "Osipov")
        masteryStudent.scholarship = BigDecimal(3000)
        for (i in 1..7) {
            masteryStudent.setMark(4)
        }
        assertEquals(BigDecimal.valueOf(3900.0), masteryStudent.countScholarship())
    }

    @Test
    fun `test scholarship for Mastery A student` () {
        val masteryStudent = MasteryStudent("Nikolai", "Osipov")
        masteryStudent.scholarship = BigDecimal(3000)
        for (i in 1..7) {
            masteryStudent.setMark(5)
        }
        assertEquals(BigDecimal.valueOf(4200.0), masteryStudent.countScholarship())
    }

    @Test
    fun `test scholarship for Mastery A student with practice` () {
        val masteryStudent = MasteryStudent("Nikolai", "Osipov")
        masteryStudent.scholarship = BigDecimal(3000)
        masteryStudent.setPracticePlace("Tinkoff", 7)
        for (i in 1..7) {
            masteryStudent.setMark(5)
        }
        assertEquals(BigDecimal.valueOf(5100.0), masteryStudent.countScholarship())
    }

    @Test
    fun `test scholarship for Mastery B student with practice` () {
        val masteryStudent = MasteryStudent("Nikolai", "Osipov")
        masteryStudent.scholarship = BigDecimal(3000)
        masteryStudent.setPracticePlace("Tinkoff", 7)
        for (i in 1..7) {
            masteryStudent.setMark(4)
        }
        assertEquals(BigDecimal.valueOf(4800.0), masteryStudent.countScholarship())
    }

    @Test
    fun `test scholarship for Mastery C student with practice` () {
        val masteryStudent = MasteryStudent("Nikolai", "Osipov")
        masteryStudent.scholarship = BigDecimal(3000)
        masteryStudent.setPracticePlace("Tinkoff", 7)
        for (i in 1..7) {
            masteryStudent.setMark(3)
        }
        assertEquals(BigDecimal.valueOf(4500.0), masteryStudent.countScholarship())
    }



}