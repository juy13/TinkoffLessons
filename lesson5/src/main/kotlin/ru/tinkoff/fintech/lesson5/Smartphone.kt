package ru.tinkoff.fintech.lesson5

import java.math.BigDecimal

data class Smartphone (
    val name : String,
    val model : String,
    val type : String,
    val price : BigDecimal,
    val OSVersion : String)

enum class Characteristics {
    NAME, MODEL, TYPE, PRICE, OSVERSION
}