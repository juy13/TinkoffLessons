class BachelorStudent (firstName : String, lastName : String) : Student(firstName, lastName) {

    private lateinit var curator : String

    override fun getYearsOfStudy() = "4 years"

    @JvmName("setCurator1")
    fun setCurator(curator : String) {
        this.curator = curator
    }


}