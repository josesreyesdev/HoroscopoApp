package com.example.horoscopoapp.data.network

import com.example.horoscopoapp.data.network.model.HoroscopeResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroscopeApi {

    @POST(".")
    suspend fun getHoroscope(
        @Query("sign") sign: String,
        @Query("day") day: String
    ): Response<HoroscopeResponse>


}