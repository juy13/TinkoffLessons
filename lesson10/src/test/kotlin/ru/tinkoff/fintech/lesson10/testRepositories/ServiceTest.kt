package ru.tinkoff.fintech.lesson10.testRepositories

import org.awaitility.Awaitility.await
import org.awaitility.kotlin.matches
import org.awaitility.kotlin.untilCallTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ru.tinkoff.fintech.lesson10.student.ProducerService
import org.springframework.beans.factory.annotation.Autowired
import ru.tinkoff.fintech.lesson10.interfaces.JpaEventRepo
import ru.tinkoff.fintech.lesson10.student.model.Status
import java.lang.Thread.sleep



@SpringBootTest
class ScheduledAwaitilityIntegrationTest {


    @Autowired
    private lateinit var producer: ProducerService

    @Autowired
    private lateinit var jpaEventRepo: JpaEventRepo

//    @MockkBean
//    private lateinit var smsService: SMSService
//    @MockkBean
//    private lateinit var emailService: EmailService
//    @MockkBean
//    private lateinit var pushService: PushService

    @Test
    fun `test update status to DONE`() {

        producer.produce()

        await() untilCallTo {
            jpaEventRepo.findAll()
        } matches {
            it!!.all { event -> event.status == Status.DONE }
        }
        sleep(1000)
        val result = jpaEventRepo.findAll()

        result.forEach{
            assertEquals(Status.DONE, it.status)
        }
    }

//    @Test
//    fun `test update status to IN_Progress`() {
//
//        producer.produce()
//        //producer.sendMessageToDestination(Event(5, Types.SMS, "qwerty", Status.NEW))
//
//        await() untilCallTo {
//            jpaEventRepo.findAll()
//        } matches {
//            it!!.all { event -> event.status == Status.DONE }
//        }
//
//        verify(exactly = 1) {smsService.push(any(), any())}
//        verify(exactly = 1) {emailService.push(any(), any())}
//        verify(exactly = 1) {pushService.push(any(), any())}
//    }
}