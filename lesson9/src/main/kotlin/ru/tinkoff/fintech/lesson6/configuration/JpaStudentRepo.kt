package ru.tinkoff.fintech.lesson6.configuration

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

interface JpaStudentRepo : JpaRepository<StudentInfo, Int> {


}