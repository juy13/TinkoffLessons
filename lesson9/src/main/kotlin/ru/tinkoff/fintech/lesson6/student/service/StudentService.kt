package ru.tinkoff.fintech.lesson6.student.service

import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson6.repository.StudentRepository
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo
import kotlinx.coroutines.*
import javax.xml.bind.JAXBElement
import kotlin.coroutines.*

@Service
class StudentService (private val studentRepository: StudentRepository) {

    val scope = CoroutineScope(Dispatchers.Default)

    fun getStudent(studentId: Int): StudentInfo {
        require(studentId >= 0)
        return studentRepository.getStudent(studentId)
    }

    fun newStudent(studentInfo: StudentInfo): String
    {
        scope.launch {
            studentRepository.newStudent(studentInfo)
        }
        return "Got data"
    }

}