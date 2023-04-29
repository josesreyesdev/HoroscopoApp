package com.example.horoscopoapp.data.network.model

import com.example.horoscopoapp.domain.model.HoroscopeModel
import com.squareup.moshi.Json

data class HoroscopeResponse(
    @Json(name = "date")val date: String,
    @Json(name = "horoscope")val horoscope: String,
    @Json(name = "icon")val icon: String,
    @Json(name = "id")val id: Int,
    @Json(name = "sign")val sign: String
)

fun HoroscopeResponse.toDomain() = HoroscopeModel(
        horoscope = this.horoscope,
        icon = this.icon,
        sign = this.sign
    )