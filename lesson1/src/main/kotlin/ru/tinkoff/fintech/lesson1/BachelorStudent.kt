package ru.tinkoff.fintech.lesson1

import java.math.BigDecimal

class BachelorStudent(firstName: String, lastName: String) : Student(firstName, lastName) {

    private lateinit var curator: String
    override var scholarship: BigDecimal = BigDecimal.valueOf(0)

    override fun getYearsOfStudy() = "4 years"

    fun setCurator(curator: String) {
        this.curator = curator
    }

    override fun countScholarship(): BigDecimal {
        return scholarship + (scholarship *
                when {
                    (this.getMiddleMark() >= 3 && this.getMiddleMark() < 4) -> BigDecimal.valueOf(0.2)
                    (this.getMiddleMark() >= 4 && this.getMiddleMark() < 5) -> BigDecimal.valueOf(0.3)
                    (this.getMiddleMark() == 5F) -> BigDecimal.valueOf(0.4)
                    else -> BigDecimal.valueOf(0.0)
                })
    }


}