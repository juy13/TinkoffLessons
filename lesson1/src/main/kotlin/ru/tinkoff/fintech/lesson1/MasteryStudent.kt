package ru.tinkoff.fintech.lesson1

import java.math.BigDecimal

class MasteryStudent(firstName: String, lastName: String) : Student(firstName, lastName) {

    private var practicePlace: String = ""
    private var practiceTimeHours = 16
    private var timePerWeek = 0
    override var scholarship: BigDecimal = BigDecimal.valueOf(0)

    override fun getYearsOfStudy() = "2 years"

    override fun countScholarship(): BigDecimal {
        return if (practicePlace.isNotEmpty()) {
            scholarship + scholarship * (BigDecimal.valueOf(0.3)) + (scholarship *
                    when {
                        (this.getMiddleMark() >= 3 && this.getMiddleMark() < 4) -> BigDecimal.valueOf(0.2)
                        (this.getMiddleMark() > 4 && this.getMiddleMark() < 5) -> BigDecimal.valueOf(0.3)
                        (this.getMiddleMark() == 5F) -> BigDecimal.valueOf(0.4)
                        else -> BigDecimal.valueOf(0.0)
                    })
        } else {
            scholarship + (scholarship *
                    when {
                        (this.getMiddleMark() >= 3 && this.getMiddleMark() < 4) -> BigDecimal.valueOf(0.2)
                        (this.getMiddleMark() > 4 && this.getMiddleMark() < 5) -> BigDecimal.valueOf(0.3)
                        (this.getMiddleMark() == 5F) -> BigDecimal.valueOf(0.4)
                        else -> BigDecimal.valueOf(0.0)
                    })
        }
    }

    fun setPracticePlace(practicePlace: String, timePerWeek: Int) {
        this.practicePlace = practicePlace
        this.timePerWeek = timePerWeek
    }

    fun go2practicePlace() {
        practiceTimeHours -= timePerWeek
    }

    fun practiceTimeLeft() = practiceTimeHours
}