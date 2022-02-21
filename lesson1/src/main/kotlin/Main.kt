import kotlin.random.Random


fun main(args: Array<String>) {

    println("Create a bachelor student Ivan Ivonov")
    val bachelorStudent = BachelorStudent("Ivan", "Ivanov")

    println("Create a mastery student Nikolai Osipov")
    val masteryStudent = MasteryStudent("Nikolai", "Osipov")

    println("Studying years of bachelor student : ${bachelorStudent.getYearsOfStudy()} ")
    println("Studying years of mastery student : ${masteryStudent.getYearsOfStudy()} ")

    println("Set a practice place for mastery student")
    masteryStudent.go2practicePlace("Tinkoff")
    println("Set a curator for bachelor student ")
    bachelorStudent.setCurator("Dmitrii Smirnov")

    val universityStudents = listOf(bachelorStudent, masteryStudent)
    universityStudents.forEach { it.printName() }

    println("Set marks")
    for (i in 1..6) {
        bachelorStudent.setMark(Random.nextInt(3, 5))
    }
    for (i in 1..7) {
        masteryStudent.setMark(Random.nextInt(3, 5))
    }

    val infoStudent = InfoStudent(bachelorStudent)
    val infoStudent2 = InfoStudent(masteryStudent)
    infoStudent.getInfoStudent()
    infoStudent2.getInfoStudent()


}