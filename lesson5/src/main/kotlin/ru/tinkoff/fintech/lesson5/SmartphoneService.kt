package ru.tinkoff.fintech.lesson5

import java.math.BigDecimal

object SmartphoneService {

    fun groupingByType(collection: Collection<Smartphone>, typeOfGrouping: Characteristics): Map<Any, List<Smartphone>> {
        return when (typeOfGrouping) {
            Characteristics.TYPE -> collection.group{it.type}
            Characteristics.MODEL -> collection.group{it.model}
            Characteristics.NAME ->  collection.group{it.name}
            Characteristics.OSVERSION ->  collection.group{it.OSVersion}
            Characteristics.PRICE ->  collection.group{it.price}
        }
    }

    fun <T>filterByPrice(collection: Collection<Smartphone>, opr : (T) -> Boolean): List<String> {
        val filteredList = collection.filterWith3 { opr(it.price as T) }
        val retList = mutableListOf<String>()
        for (element in filteredList) {
            retList.add(element.name)
        }
        return retList
    }

    fun translateEsp(collection: Collection<Smartphone>) : Collection<Smartphone>{
        val translatedCollection = mutableListOf<Smartphone>()
        for (element in collection) {
            translatedCollection.add(Smartphone(spanishWords(element.name),
                spanishWords(element.model),
                spanishWords(element.type),
                exchangeUSD2EUR(element.price),
                spanishWords(element.OSVersion)))
        }
        return translatedCollection
    }

    private fun spanishWords(word : String) : String {
        return when (word) {
            "Apple" -> "Manzana"
            "smartphone" -> "teléfono inteligente"
            "bar" -> "bar"
            "candy bar" -> "barra de caramelo"
            else -> {   "some Espanol " }
        }
    }

    private fun exchangeUSD2EUR (price : BigDecimal): BigDecimal {
        val USD2EUR = BigDecimal.valueOf(0.91)
        return price * USD2EUR
    }

    private fun <T, K> Iterable<K>.group(key: (K) -> T) : Map<T, List<K>> {
        val destination = LinkedHashMap<T, MutableList<K>>()
        for (element in this) {
            val key2 = key(element)
            val list = destination.getOrPut(key2) { ArrayList<K>() }
            list.add(element)
        }
        return destination
    }

    private fun <K> Iterable<K>.filterWith3(key: (K) -> Boolean) : List<K> {
        val destination = mutableListOf<K>()
        var i = 0
        for (element in this) {
            if (key(element) && i < 3) {
                destination.add(element)
                i += 1
            }

        }
        return destination
    }



}