package ru.tinkoff.fintech.lesson5

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

    private val smartphones = listOf(iphone, samsungA32, htcWindowsPhone, blackBerryClassic, nokia3310, nokia3210)

    @Test
    fun `test grouping function` () {
        println(SmartphoneService.groupingByType(smartphones, 1))
    }
}