package ru.tinkoff.fintech.lesson10.repository

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson10.interfaces.EventRepository
import ru.tinkoff.fintech.lesson10.interfaces.JpaEventRepo
import ru.tinkoff.fintech.lesson10.student.model.Event


@Service
class JpaEventRepositoryImpl(private val jpaStudentRepository: JpaEventRepo) : EventRepository {

    override fun search4NewEvents(): List<Event> {
        val events = jpaStudentRepository.search4NewEvents()
        if (events.isEmpty()) {
            return listOf()
        }
        return events
    }

    override fun getAll(): List<Event> {
        return jpaStudentRepository.findAll()
    }

    override fun updateNewEv(id: Int) {
        jpaStudentRepository.updateNewEv(id)
    }

    override fun doneEvent(id: Int) {
        jpaStudentRepository.doneEvent(id)
    }


}
