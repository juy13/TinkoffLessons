package ru.tinkoff.fintech.lesson6.repository

import ru.tinkoff.fintech.lesson6.student.model.Events



interface EventRepository {

    fun search4NewEvents() : List<Events>

}