package ru.tinkoff.fintech.lesson10.student

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.lesson10.repository.JpaEventRepositoryImpl
import ru.tinkoff.fintech.lesson10.student.model.Event
import ru.tinkoff.fintech.lesson10.student.model.Status


@Component
class ProducerService() {

    @Autowired
    private val jpaEventRepositoryImpl: JpaEventRepositoryImpl? = null

    @Autowired
    private val jmsTemplate: JmsTemplate? = null

    @Scheduled(cron = "*/5 * * * * ?")
    fun produce() {
        val newEvents = jpaEventRepositoryImpl?.search4NewEvents()
        newEvents?.forEach {
            if (it.status != Status.ERROR) {
                jpaEventRepositoryImpl?.updateNewEv(it.id)
                sendMessageToDestination(it)
            }
        }
    }

    fun sendMessageToDestination(message: Event) {
        val gson = Gson()
        val jsonString = gson.toJson(message)
        jmsTemplate!!.convertAndSend(jsonString)
    }

}