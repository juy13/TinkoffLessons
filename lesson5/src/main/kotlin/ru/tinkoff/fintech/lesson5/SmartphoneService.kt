package ru.tinkoff.fintech.lesson5

import java.math.BigDecimal

object SmartphoneService {

    fun groupingByType(collection: Collection<Smartphone>, typeOfGrouping: Characteristics): Map<Any, List<Smartphone>> {
        return when (typeOfGrouping) {
            Characteristics.TYPE -> collection.groupBy{it.type}
            Characteristics.MODEL -> collection.groupBy{it.model}
            Characteristics.NAME ->  collection.groupBy{it.name}
            Characteristics.OSVERSION ->  collection.groupBy{it.OSVersion}
            Characteristics.PRICE ->  collection.groupBy{it.price}
        }
    }

    fun filterByPrice(collection: Collection<Smartphone>, opr: (Smartphone) -> Boolean): List<String> =
        collection.asSequence().filter { opr(it) }.map { it.name }.take(3).toList()

    fun translate(
        collection: Collection<Smartphone>, opr: (String) -> String,
        conversation: (BigDecimal) -> BigDecimal
    ):
            List<Smartphone> = collection.asSequence().map {
        Smartphone(
            opr(it.name),
            opr(it.model),
            opr(it.type),
            conversation(it.price),
            opr(it.OSVersion)
        )
    }.sortedBy { it.price }.toList()



}