package ru.tinkoff.fintech.lesson6.repository

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson6.configuration.JpaEventRepo
import ru.tinkoff.fintech.lesson6.student.model.Events


@Service
class JpaEventRepositoryImpl (private val jpaStudentRepository: JpaEventRepo) : EventRepository {

    override fun search4NewEvents(): List<Events> {
        val events = jpaStudentRepository.findAll()
        if (events.isEmpty()) {
            return listOf(Events())
        }
        return events
    }


}