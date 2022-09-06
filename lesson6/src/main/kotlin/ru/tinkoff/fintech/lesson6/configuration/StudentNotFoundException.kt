package ru.tinkoff.fintech.lesson6.configuration

class StudentNotFoundException(id: Int?) : RuntimeException(String.format("Student with Id %d not found", id))