class MasteryStudent (firstName : String, lastName : String) : Student(firstName, lastName) {

    private lateinit var practicePlace : String

    override fun getYearsOfStudy() = "2 years"

    fun go2practicePlace (practicePlace: String) {
        this.practicePlace = practicePlace
    }
}