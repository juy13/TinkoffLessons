package ru.tinkoff.fintech.lesson1

import ru.tinkoff.fintech.lesson1.Student

class BachelorStudent (firstName : String, lastName : String) : Student(firstName, lastName) {

    private lateinit var curator : String
    override var scholarship: Int = 0

    override fun getYearsOfStudy() = "4 years"

    fun setCurator(curator : String) {
        this.curator = curator
    }

    override fun countScholarship(): Float {
        return scholarship.toFloat() + (scholarship.toFloat()  *
                when {
                    (this.getMiddleMark() >= 3 && this.getMiddleMark() < 4) ->  0.2F
                    (this.getMiddleMark() > 4 && this.getMiddleMark() < 5) -> 0.3F
                    (this.getMiddleMark() == 5F) -> 0.5F
                    else -> 0.0F
                })
    }


}