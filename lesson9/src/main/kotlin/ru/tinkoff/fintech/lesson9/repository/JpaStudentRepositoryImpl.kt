package ru.tinkoff.fintech.lesson9.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.util.ObjectUtils
import ru.tinkoff.fintech.lesson9.configuration.JpaStudentRepo
import ru.tinkoff.fintech.lesson9.student.model.StudentInfo

//@Primary
@Service
class JpaStudentRepositoryImpl (private val jpaStudentRepository: JpaStudentRepo) : StudentRepository {

    override suspend fun getStudent(studentId: Int): StudentInfo {
        return withContext(Dispatchers.IO) {
            val student = jpaStudentRepository.findById(studentId)
            if (student.isPresent) {
                return@withContext student.get()
            }
            return@withContext StudentInfo()
        }
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