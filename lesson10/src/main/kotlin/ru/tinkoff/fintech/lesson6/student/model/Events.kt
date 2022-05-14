package ru.tinkoff.fintech.lesson6.student.model

import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor
@Table(name = "events")
data class Events(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val type: Types = Types.NO,
    val body: String  = "unknown",
    val status: Status = Status.ERROR
)

enum class Types {
    SMS,
    EMAIL,
    PUSH,
    NO
}

enum class Status {
    NEW,
    IN_PROCESS,
    DONE,
    ERROR
}