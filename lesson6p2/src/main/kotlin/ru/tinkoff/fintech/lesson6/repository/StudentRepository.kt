package ru.tinkoff.fintech.lesson6.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo


@Repository
interface StudentRepository : JpaRepository<StudentInfo, Int> {
    @Query("select s from StudentInfo s where s.firstName = :firstName AND s.lastName = :lastName AND s.degree = :degree")
    fun findByNameAndDegree(
        @Param("firstName") firstName: String,
        @Param("lastName") lastName: String,
        @Param("degree") degree : String = "bachelor"
    ): StudentInfo?
}