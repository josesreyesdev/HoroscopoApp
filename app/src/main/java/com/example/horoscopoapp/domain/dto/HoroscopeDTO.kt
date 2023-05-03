package com.example.horoscopoapp.domain.dto

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class HoroscopeDTO(
    val sign: String,
    val date:String = getCurrentDay(),
    val lang: String = "es"
)

fun getCurrentDay(): String {
    val currentDay = Date() /*LocalDate.now()*/
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(currentDay)
}
