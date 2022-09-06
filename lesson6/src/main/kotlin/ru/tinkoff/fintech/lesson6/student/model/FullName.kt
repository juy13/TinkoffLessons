package ru.tinkoff.fintech.lesson6.student.model

data class FullName(
    val firstName: String,
    val lastName: String
) {
    operator fun contains(fullName: FullName): Boolean {
        return fullName.firstName == firstName && fullName.lastName == lastName
    }
}

