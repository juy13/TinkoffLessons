package ru.tinkoff.fintech.lesson1

class MasteryStudent (firstName : String, lastName : String) : Student(firstName, lastName) {

    private lateinit var practicePlace : String
    private var practiceTimeHours = 16
    private var timePerWeek = 0
    override var scholarship : Int = 0

    override fun getYearsOfStudy() = "2 years"

    override fun countScholarship(): Float {
        return if (this::practicePlace.isInitialized) {
            scholarship.toFloat() + (scholarship.toFloat() * 0.3F) + (scholarship.toFloat()  *
                    when {
                        (this.getMiddleMark() >= 3 && this.getMiddleMark() < 4) ->  0.2F
                        (this.getMiddleMark() > 4 && this.getMiddleMark() < 5) -> 0.3F
                        (this.getMiddleMark() == 5F) -> 0.5F
                        else -> 0.0F
                    })
        } else {
            scholarship.toFloat() + (scholarship.toFloat()  *
                    when {
                        (this.getMiddleMark() >= 3 && this.getMiddleMark() < 4) ->  0.2F
                        (this.getMiddleMark() > 4 && this.getMiddleMark() < 5) -> 0.3F
                        (this.getMiddleMark() == 5F) -> 0.5F
                        else -> 0.0F
                    })
        }
    }

    fun setPracticePlace (practicePlace: String, timePerWeek : Int) {
        this.practicePlace = practicePlace
        this.timePerWeek = timePerWeek
    }

    fun go2practicePlace() {
        practiceTimeHours -= timePerWeek
    }

    fun practiceTimeLeft() = practiceTimeHours
}