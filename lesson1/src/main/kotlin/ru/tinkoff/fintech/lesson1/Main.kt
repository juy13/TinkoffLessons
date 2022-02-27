package ru.tinkoff.fintech.lesson1

import java.math.BigDecimal
import kotlin.random.Random


fun main() {

    println("Create a bachelor student Ivan Ivonov")
    val bachelorStudent = BachelorStudent("Ivan", "Ivanov")

    println("Create a mastery student Nikolai Osipov")
    val masteryStudent = MasteryStudent("Nikolai", "Osipov")

    println("Studying years of bachelor student : ${bachelorStudent.getYearsOfStudy()} ")
    println("Studying years of mastery student : ${masteryStudent.getYearsOfStudy()} ")

    println("Set a practice place for mastery student")
    masteryStudent.setPracticePlace("Tinkoff", 7)
    println("Set a curator for bachelor student ")
    bachelorStudent.setCurator("Dmitrii Smirnov")

    println("Set a scholarship for mastery student")
    masteryStudent.scholarship = BigDecimal(3000)
    println("Set a scholarship for bachelor student ")
    bachelorStudent.scholarship = BigDecimal(3000)

    println("Now mastery student go to practice")
    masteryStudent.go2practicePlace()
    println("How much time for practice left : ${masteryStudent.practiceTimeLeft()}")


    val universityStudents = listOf(bachelorStudent, masteryStudent)
    universityStudents.forEach { it.printName() }

    println("Set marks")
    for (i in 1..6) {
        bachelorStudent.setMark(Random.nextInt(3, 5))
    }
    for (i in 1..7) {
        masteryStudent.setMark(Random.nextInt(3, 5))
    }

    println("Mastery student scholarship : ${masteryStudent.countScholarship().setScale(2)}")
    println("Bachelor student scholarship : ${bachelorStudent.countScholarship().setScale(2)}")

    val infoStudent = InfoStudent(bachelorStudent)
    val infoStudent2 = InfoStudent(masteryStudent)
    infoStudent.getInfoStudent()
    infoStudent2.getInfoStudent()


}