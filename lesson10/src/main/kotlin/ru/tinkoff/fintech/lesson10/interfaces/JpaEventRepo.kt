package ru.tinkoff.fintech.lesson10.interfaces

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import ru.tinkoff.fintech.lesson10.student.model.Event

//where s.status = 'NEW'
@Transactional
interface JpaEventRepo : JpaRepository<Event, Int> {

    @Query("select * from events s where s.status = 'NEW'", nativeQuery = true)
    fun search4NewEvents(): List<Event>

    @Modifying
    @Query("UPDATE events SET status = 'IN_PROCESS' WHERE id = :id",
        nativeQuery = true)
    fun updateNewEv(@Param("id") id : Int)

    @Modifying
    @Query("UPDATE events SET status = 'DONE' WHERE id = :id",
        nativeQuery = true)
    fun doneEvent(@Param("id") id : Int)
}