//package ru.tinkoff.fintech.lesson10.testRepositories
//
//import com.google.gson.Gson
//import org.awaitility.Awaitility.await
//import org.awaitility.Durations
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito.*
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.boot.test.mock.mockito.SpyBean
//import org.springframework.context.annotation.PropertySource
//import org.springframework.jms.core.JmsTemplate
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
//import org.springframework.test.context.junit4.SpringRunner
//import ru.tinkoff.fintech.lesson10.configuration.JmsConfig
//import ru.tinkoff.fintech.lesson10.configuration.JpaEventRepo
//import ru.tinkoff.fintech.lesson10.repository.EventRepository
//import ru.tinkoff.fintech.lesson10.repository.JpaEventRepositoryImpl
//import ru.tinkoff.fintech.lesson10.student.ProducerService
//import ru.tinkoff.fintech.lesson10.student.model.Events
//import ru.tinkoff.fintech.lesson10.student.model.Status
//import ru.tinkoff.fintech.lesson10.student.model.Types
//
//
//@SpringJUnitConfig(JmsConfig::class)
//@DataJpaTest
//@EnableAutoConfiguration
//class ScheduledAwaitilityIntegrationTest {
//
//    @Autowired
//    private lateinit var eventRepo: JpaEventRepo
////
////    private val jpaEventRepositoryImpl = JpaEventRepositoryImpl(eventRepo)
////    private val producerService = ProducerService(jpaEventRepositoryImpl)
//
////    @Autowired
////    private lateinit var counter: ProducerService
//
//    @Autowired
//    private val jmsTemplate: JmsTemplate? = null
//
////    @BeforeEach
////    fun init() {
////        counter = ProducerService()
////    }
//
//    @Test
//    fun whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() {
//        val event = Events(1, Types.SMS, "qwerty", Status.NEW)
//        sendMessageToDestination(event)
//    }
//
//    private fun sendMessageToDestination(message: Events) {
//        val gson = Gson()
//        val jsonString = gson.toJson(message)
//        jmsTemplate!!.convertAndSend(jsonString)
//    }
//
//
//
//
//}