package ru.tinkoff.fintech.lesson5

object SmartphoneService {

    fun groupingByType(collection: Collection<Smartphone>, typeOfGrouping: Int): Map<String, List<Smartphone>>? {
        when (typeOfGrouping) {
            1 -> return collection.groupBy { it.type }
            2 -> return collection.groupBy { it.type }
        }
        return null
    }

}