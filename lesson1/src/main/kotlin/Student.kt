abstract class Student (open val firstName : String, open val lastName : String) {

    private var middleMark : Float = 0F
    private val listOfMarks : MutableList<Int> = mutableListOf<Int>()

    fun printName() = println("First name : $firstName, Last name : $lastName")

    abstract fun getYearsOfStudy () : String
    fun setMark (mark : Int) {
        listOfMarks.add(mark)
        middleMark = (listOfMarks.sum().toFloat() / listOfMarks.size.toFloat())
    }

    fun getMiddleMark() = middleMark

}