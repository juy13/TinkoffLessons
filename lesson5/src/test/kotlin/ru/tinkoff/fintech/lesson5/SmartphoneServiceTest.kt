package ru.tinkoff.fintech.lesson5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class SmartphoneServiceTest {

    private val iphone = Smartphone("Apple",
        "Iphone 12",
        "smartphone",
        BigDecimal(1000),
        "IOS 15")

    private val samsungA32 = Smartphone("Samsung",
        "Galaxy A32",
        "smartphone",
        BigDecimal(400),
        "Android 12")

    private val nokia3310 = Smartphone("Nokia",
        "3310",
        "bar",
        BigDecimal(100),
        "Series 20")

    private val nokia3210 = Smartphone("Nokia",
        "3210",
        "bar",
        BigDecimal(100),
        "Series 20")

    private val blackBerryClassic = Smartphone("BlackBerry",
        "Classic",
        "candy bar",
        BigDecimal(340),
        "BlackBerry 10.3.3")

    private val htcWindowsPhone = Smartphone("HTC",
        "Windows Phone 8X",
        "smartphone",
        BigDecimal(560),
        "Microsoft Windows Phone 8")

    private val smartphones = sequenceOf(iphone, samsungA32, htcWindowsPhone, blackBerryClassic, nokia3310, nokia3210)

    @Test
    fun `test grouping smartphone function` () {
        val groupedSmartphones = SmartphoneService.groupingByType(smartphones, Characteristics.TYPE)
        val amountOfSmartphonesType = groupedSmartphones["smartphone"]?.size

        assertEquals(3, amountOfSmartphonesType)
    }

    @Test
    fun `test grouping price function` () {
        val groupedSmartphones = SmartphoneService.groupingByType(smartphones, Characteristics.PRICE)
        val firstKey = groupedSmartphones.keys.first().toString()

        assertEquals("1000", firstKey)
    }

    @Test
    fun `test filter price function` () {
        val filteredSmartphones = SmartphoneService.filterByPrice(smartphones) { b: Int -> b > 100 }
        val smartphonesList = listOf(iphone, samsungA32, htcWindowsPhone)

        var i = 0
        while (i < 3) {
            assertEquals(smartphonesList[i].name, filteredSmartphones[i])
            i += 1
        }
    }

    @Test
    fun `test translate function and price` () {
        val translate2Esp: (String) -> String = { a: String
            ->
            when (a) {
                "Apple" -> "Manzana"
                "smartphone" -> "teléfono inteligente"
                "bar" -> "bar"
                "candy bar" -> "barra de caramelo"
                else -> {
                    "some Espanol "
                }
            }
        }
        val USD2EUR: (BigDecimal) -> BigDecimal = { a: BigDecimal ->
            a * BigDecimal.valueOf(0.91)
        }

        val filteredSmartphones = SmartphoneService.translate(smartphones, translate2Esp, USD2EUR)

        assertEquals(BigDecimal.valueOf(910).setScale(2), filteredSmartphones.first().price)
    }

    @Test
    fun `test translate function and translation` () {
        val translate2Esp: (String) -> String = { a: String
            ->
            when (a) {
                "Apple" -> "Manzana"
                "smartphone" -> "teléfono inteligente"
                "bar" -> "bar"
                "candy bar" -> "barra de caramelo"
                else -> {
                    "some Espanol "
                }
            }
        }
        val USD2EUR: (BigDecimal) -> BigDecimal = { a: BigDecimal ->
            a * BigDecimal.valueOf(0.91)
        }

        val filteredSmartphones = SmartphoneService.translate(smartphones, translate2Esp, USD2EUR)

        assertEquals("Manzana", filteredSmartphones.first().name)
    }

}