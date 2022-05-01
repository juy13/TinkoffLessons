package ru.tinkoff.fintech.lesson6.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.util.ObjectUtils
import ru.tinkoff.fintech.lesson6.configuration.JpaStudentRepo
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo

//@Primary
@Service
class JpaStudentRepositoryImpl (private val jpaStudentRepository: JpaStudentRepo) : StudentRepository {

    override fun getStudent(studentId: Int): StudentInfo {
        val student = jpaStudentRepository.findById(studentId)
        if (student.isPresent) {
            return student.get()
        }
        return StudentInfo()
    }

    override suspend fun newStudent(studentInfo: StudentInfo) {
        withContext(Dispatchers.IO) {
            val student = jpaStudentRepository.save(studentInfo)
            if (ObjectUtils.isEmpty(student)) {
                return@withContext "error"
            }
            return@withContext "Got it"
        }
    }


}