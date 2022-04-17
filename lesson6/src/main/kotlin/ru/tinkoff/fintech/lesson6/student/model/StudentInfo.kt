package ru.tinkoff.fintech.lesson6.student.model

//class StudentInfo() {
//    constructor(id : Int,
//                degree : String,
//                firstName: String,
//                lastName: String,
//                middleMark : Float,
//                mark : String,
//                comment : String): this() {
//         id
//         degree
//         firstName
//         lastName
//         middleMark
//         mark
//         comment
//    }
//
//    var id : Int = 0
//    lateinit var degree : String
//    lateinit var firstName: String
//    lateinit var lastName: String
//    var middleMark : Float = 0.0f
//    lateinit var mark : String
//    lateinit var comment : String
//
//    override fun equals(other: Any?): Boolean {
//        if(other is StudentInfo){
//            if( other.comment == comment &&
//                other.id == id &&
//                other.degree == degree &&
//                other.firstName == firstName &&
//                other.lastName == lastName &&
//                other.mark == mark &&
//                other.middleMark == middleMark)
//                return true
//        }
//        return false
//    }
//}

data class StudentInfo(
    val id: Int = 0,
    val degree: String = "unknown",
    val firstName: String  = "unknown",
    val lastName: String  = "unknown",
    val middleMark: Float = 0F,
    val mark: String  = "unknown",
    val comment: String  = "unknown"
)