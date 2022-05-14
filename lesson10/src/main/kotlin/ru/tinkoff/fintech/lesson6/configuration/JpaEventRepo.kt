package ru.tinkoff.fintech.lesson6.configuration

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.tinkoff.fintech.lesson6.student.model.Events

interface JpaEventRepo : JpaRepository<Events, Int> {
//    @Query("select s from events s where s.status = 'NEW'")
//    fun search4NewEvents(): List<Events>
}