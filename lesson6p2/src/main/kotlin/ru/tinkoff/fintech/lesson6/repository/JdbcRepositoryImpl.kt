package ru.tinkoff.fintech.lesson6.repository


import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo


@Primary
@Service
class JdbcStudentRepositoryImpl (private val jdbcTemplate: JdbcTemplate) : StudentRepository {

    override fun getStudents(): List<StudentInfo> {
        return jdbcTemplate.query("select * from student_info s") {rs, _ ->
            StudentInfo(
                rs.getInt("id"),
                rs.getString("degree"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getFloat("middle_mark"),
                rs.getString("mark"),
                rs.getString("comment")
            )
        }
    }

    override fun getStudent(studentId: Int): StudentInfo {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM student_info WHERE id=?", BeanPropertyRowMapper(
                StudentInfo::class.java
            ), studentId
        )
            ?: StudentInfo(
                -1,
                "unknown",
                "unknown",
                "unknown",
                -1F, "unknown",
                "unknown"
            )
    }

    override fun newStudent(studentInfo: StudentInfo): StudentInfo {
        val student = jdbcTemplate.update("insert into student_info(id, comment, degree, first_name, last_name, mark, middle_mark) values (?, ?, ?, ?, ?, ?, ?)",
            studentInfo.id,studentInfo.comment, studentInfo.degree, studentInfo.firstName, studentInfo.lastName, studentInfo.mark, studentInfo.middleMark)
        return getStudent(studentInfo.id)
    }


    override fun search4Students(degree : String): List<StudentInfo> {
        return jdbcTemplate.query(
            "SELECT * FROM student_info WHERE degree=?", BeanPropertyRowMapper(
                StudentInfo::class.java
            ), degree
        )
    }
}