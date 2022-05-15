package ru.tinkoff.fintech.lesson10.student

import com.google.gson.Gson
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.lesson10.repository.JpaEventRepositoryImpl
import ru.tinkoff.fintech.lesson10.student.model.Events
import ru.tinkoff.fintech.lesson10.student.model.Types
import ru.tinkoff.fintech.lesson10.student.service.EmailService
import ru.tinkoff.fintech.lesson10.student.service.PushService
import ru.tinkoff.fintech.lesson10.student.service.SMSService
import javax.jms.JMSException
import javax.jms.Message
import javax.jms.MessageListener
import javax.jms.TextMessage


@Component
class Consumer (jpaEventRepositoryImpl: JpaEventRepositoryImpl) : MessageListener {

    private val smsService = SMSService(jpaEventRepositoryImpl)
    private val emailService = EmailService(jpaEventRepositoryImpl)
    private val pushService = PushService(jpaEventRepositoryImpl)
    private val gson = Gson()

    override fun onMessage(message: Message) {
        if (message is TextMessage) {
            try {
                val event = gson.fromJson(message.text, Events::class.java)
                when (event.type) {
                    Types.SMS -> smsService.push(event.body, event.id)
                    Types.EMAIL -> emailService.push(event.body, event.id)
                    Types.PUSH -> pushService.push(event.body, event.id)
                    else -> {
                        println("Undefined service")
                    }
                }
                println("Message has been consumed : $event")
            } catch (ex: JMSException) {
                throw RuntimeException(ex)
            }
        } else {
            throw IllegalArgumentException("Message must be of type TextMessage")
        }
    }
}