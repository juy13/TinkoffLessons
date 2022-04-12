package ru.tinkoff.fintech.lesson6.student.model

class StudentInfo() {
    constructor(id : Int,
                degree : String,
                firstName: String,
                lastName: String,
                middleMark : Float,
                mark : String,
                comment : String): this() {
        this.id = id
        this.degree = degree
        this.firstName = firstName
        this.lastName = lastName
        this.middleMark = middleMark
        this.mark = mark
        this.comment = comment
    }

    var id : Int = 0
    lateinit var degree : String
    lateinit var firstName: String
    lateinit var lastName: String
    var middleMark : Float = 0.0f
    lateinit var mark : String
    lateinit var comment : String

    override fun equals(other: Any?): Boolean {
        if(other is StudentInfo){
            if( other.comment == comment &&
                other.id == id &&
                other.degree == degree &&
                other.firstName == firstName &&
                other.lastName == lastName &&
                other.mark == mark &&
                other.middleMark == middleMark)
                return true
        }
        return false
    }
}