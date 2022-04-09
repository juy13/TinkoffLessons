package ru.tinkoff.fintech.lesson6.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo


@Repository
interface StudentRepository : JpaRepository<StudentInfo?, Long?> {
//    fun findByName(productName: String?): StudentInfo?
}