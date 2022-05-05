package ru.tinkoff.fintech.lesson9.configuration

import org.springframework.data.jpa.repository.JpaRepository
import ru.tinkoff.fintech.lesson9.student.model.StudentInfo

interface JpaStudentRepo : JpaRepository<StudentInfo, Int> {


}