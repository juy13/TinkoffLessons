package ru.tinkoff.fintech.lesson10.interfaces

import ru.tinkoff.fintech.lesson10.student.model.Event


interface EventRepository {

    fun search4NewEvents() : List<Event>

    fun getAll() : List<Event>
    fun updateNewEv(id : Int)
    fun doneEvent(id : Int)

}