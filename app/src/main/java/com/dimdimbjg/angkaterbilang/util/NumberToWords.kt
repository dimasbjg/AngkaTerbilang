package com.dimdimbjg.angkaterbilang.util

object NumberToWords {
    val huruf = listOf<String>("",
        "Satu",
        "Dua",
        "Tiga",
        "Empat",
        "Lima",
        "Enam",
        "Tujuh",
        "Delapan",
        "Sembilan",
        "Sepuluh",
        "Sebelas")


    fun convert(number: Long): String {
        return when (number) {
            in 0..12 -> huruf[number.toInt()]
            in 12..19 -> huruf[number.toInt() % 10] + " Belas"
            in 20..99 -> huruf[number.toInt() / 10] + " Puluh " + huruf[number.toInt() % 10]
            in 100..199 -> "Seratus " + convert(number % 100)
            in 200..999 -> huruf[number.toInt() / 100] + " Ratus " + convert(number % 100)
            in 1000..1999 -> "Seribu " + convert(number % 1000)
            in 2000..999999 -> convert(number / 1000) + " Ribu " + convert(number % 1000)
            in 1000000..999999999 -> convert(number / 1000000) + " Juta " + convert(
                number % 1000000)
            in 1000000000L..999999999999L -> convert(number / 1000000000L) + " Milyar " + convert(
                number % 1000000000L)
            in 1000000000000L..999999999999999L -> convert(number / 1000000000000L) + " Triliun " + convert(
                number % 1000000000000L)
            else -> "tidak bisa merubah huruf"
        }
    }

}