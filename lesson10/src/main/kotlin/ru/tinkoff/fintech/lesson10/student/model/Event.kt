package ru.tinkoff.fintech.lesson10.student.model

import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor
@Table(name = "events")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @Enumerated(EnumType.STRING)
    val type: Types = Types.NO,
    val body: String  = "unknown",
    @Enumerated(EnumType.STRING)
    val status: Status = Status.ERROR
)

enum class Types {
    SMS,
    EMAIL,
    PUSH,
    NO
}

enum class Status(val status : String) {
    NEW("NEW"),
    IN_PROCESS("IN_PROCESS"),
    DONE("DONE"),
    ERROR("ERROR")
}