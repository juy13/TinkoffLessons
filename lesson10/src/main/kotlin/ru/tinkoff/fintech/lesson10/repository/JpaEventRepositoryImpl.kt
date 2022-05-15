package ru.tinkoff.fintech.lesson10.repository

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson10.configuration.JpaEventRepo
import ru.tinkoff.fintech.lesson10.student.model.Events


@Service
class JpaEventRepositoryImpl(private val jpaStudentRepository: JpaEventRepo) : EventRepository {

    override fun search4NewEvents(): List<Events> {
        val events = jpaStudentRepository.search4NewEvents()
        if (events.isEmpty()) {
            return listOf(Events())
        }
        return events
    }

    override fun getAll(): List<Events> {
        return jpaStudentRepository.findAll()
    }

    override fun updateNewEv(id: Int) {
        jpaStudentRepository.updateNewEv(id)
    }

    override fun doneEvent(id: Int) {
        jpaStudentRepository.doneEvent(id)
    }


}
