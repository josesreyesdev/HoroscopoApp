package com.example.horoscopoapp.data.network.model

import com.squareup.moshi.Json

data class HoroscopeResponse(
    @Json(name = "current_day")val currentDay: String,
    @Json(name = "compatibility")val compatibility: String,
    @Json(name = "lucky_time")val luckyTime: String,
    @Json(name = "lucky_number")val luckyNumber: String,
    @Json(name = "mood")val mood: String,
    @Json(name = "description")val description: String
)
