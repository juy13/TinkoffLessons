package ru.tinkoff.fintech.lesson6.student.model

import javax.persistence.*


@Entity
@Table(name = "FullName")
data class FullName(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val firstName: String,
    val lastName: String
) {
    operator fun contains(fullName: FullName): Boolean {
        return fullName.firstName == firstName && fullName.lastName == lastName
    }
}
