package ru.tinkoff.fintech.lesson5

import java.math.BigDecimal

object SmartphoneService {

    fun groupingByType(collection: Sequence<Smartphone>, typeOfGrouping: Characteristics): Map<Any, List<Smartphone>> {
        return when (typeOfGrouping) {
            Characteristics.TYPE -> collection.groupBy{it.type}
            Characteristics.MODEL -> collection.groupBy{it.model}
            Characteristics.NAME ->  collection.groupBy{it.name}
            Characteristics.OSVERSION ->  collection.groupBy{it.OSVersion}
            Characteristics.PRICE ->  collection.groupBy{it.price}
        }
    }

    fun <T> filterByPrice(collection: Sequence<Smartphone>, opr: (T) -> Boolean): List<String> =
        collection.filter { opr(it.price as T) }.map { it.name }.take(3).toList()

    fun translate(
        collection: Sequence<Smartphone>, opr: (String) -> String,
        conversation: (BigDecimal) -> BigDecimal
    ):
            List<Smartphone> = collection.map {
        Smartphone(
            opr(it.name),
            opr(it.model),
            opr(it.type),
            conversation(it.price),
            opr(it.OSVersion)
        )
    }.toList()



}