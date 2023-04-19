package com.example.horoscopoapp.domain

import com.example.horoscopoapp.data.network.HoroscopeApi
import com.example.horoscopoapp.data.network.model.HoroscopeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val api: HoroscopeApi){

    // Esta funcion automaticamente se invoka al ser llamado
     suspend operator fun invoke() : Flow<HoroscopeResponse?> {
         val response = api.getHoroscope("aries", "today")
        if (response.isSuccessful) {
            return flowOf(response.body())
        }
        return flowOf(null)
     }
}