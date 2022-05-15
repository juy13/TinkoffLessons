package ru.tinkoff.fintech.lesson10.repository

import org.springframework.data.repository.query.Param
import ru.tinkoff.fintech.lesson10.student.model.Events



interface EventRepository {

    fun search4NewEvents() : List<Events>

    fun getAll() : List<Events>
    fun updateNewEv(id : Int)
    fun doneEvent(id : Int)

}