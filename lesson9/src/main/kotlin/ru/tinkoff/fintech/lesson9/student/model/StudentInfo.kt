package ru.tinkoff.fintech.lesson9.student.model

import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor
@Table(name = "StudentInfo")
data class StudentInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val logging : String = "unknown"
)

