package ru.tinkoff.fintech.lesson1


class InfoStudent(private val student: Student) {

    fun getInfoStudent() {
        "Student ${student.lastName} ${student.firstName} has a middle mark : ${student.getMiddleMark()} ".characteristic()
    }

    private fun String.characteristic() {
        print(this)
        when {
            (student.getMiddleMark() >= 3 && student.getMiddleMark() < 4) -> println("C student")
            (student.getMiddleMark() > 4 && student.getMiddleMark() < 5) -> println("B student")
            (student.getMiddleMark() == 5F) -> println("A student")
            else -> println("Too bad")
        }

    }

}