package ru.tinkoff.fintech.lesson6.repository


import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import ru.tinkoff.fintech.lesson6.student.model.StudentInfo


@Primary
@Service
class JdbcStudentRepositoryImpl (private val jdbcTemplate: JdbcTemplate) : StudentRepository {

    private val rowMapper = RowMapper<StudentInfo>() {rs, _ ->
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

    override fun getStudents(): List<StudentInfo> {
        return jdbcTemplate.query(GetStudents) {rs, _ ->
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
            GetStudent, rowMapper, studentId)
            ?: StudentInfo()
    }

    override fun newStudent(studentInfo: StudentInfo) {
        jdbcTemplate.update(NewStudent,
            studentInfo.id,studentInfo.comment, studentInfo.degree, studentInfo.firstName, studentInfo.lastName, studentInfo.mark, studentInfo.middleMark)
    }


    override fun search4Students(degree : String): List<StudentInfo> {
        return jdbcTemplate.query(
            Search4Students, rowMapper, degree
        )
    }

    companion object {
        const val GetStudents = "select * from student_info s"
        const val GetStudent = "SELECT * FROM student_info WHERE id=?"
        const val NewStudent = "insert into student_info(id, comment, degree, first_name, last_name, mark, middle_mark) values (?, ?, ?, ?, ?, ?, ?)"
        const val Search4Students = "SELECT * FROM student_info WHERE degree=?"
    }
}