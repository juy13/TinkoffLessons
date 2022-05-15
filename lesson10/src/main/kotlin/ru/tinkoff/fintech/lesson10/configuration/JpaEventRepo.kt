package ru.tinkoff.fintech.lesson10.configuration

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import ru.tinkoff.fintech.lesson10.student.model.Events

//where s.status = 'NEW'
@Transactional
interface JpaEventRepo : JpaRepository<Events, Int> {

    @Query("select * from events s where s.status = 'NEW'", nativeQuery = true)
    fun search4NewEvents(): List<Events>

    @Modifying
    @Query("UPDATE events SET status = 'IN_PROCESS' WHERE id = :id",
        nativeQuery = true)
    fun updateNewEv(@Param("id") id : Int)

    @Modifying
    @Query("UPDATE events SET status = 'IN_PROCESS' WHERE id = :id",
        nativeQuery = true)
    fun doneEvent(@Param("id") id : Int)
}