package ru.tinkoff.fintech.lesson6.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

interface JpaStudentRepo : JpaRepository<StudentInfo, Int> {
    @Query("select s from StudentInfo s where s.degree = :degree")
    fun search4Students(
        @Param("degree") degree : String = "bachelor"
    ): List<StudentInfo>
}