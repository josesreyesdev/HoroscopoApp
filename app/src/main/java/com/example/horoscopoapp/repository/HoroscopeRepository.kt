package com.example.horoscopoapp.repository

import com.example.horoscopoapp.core.network.ResultType
import com.example.horoscopoapp.data.network.HoroscopeApi
import com.example.horoscopoapp.data.network.model.toDomain
import com.example.horoscopoapp.domain.dto.HoroscopeDTO
import com.example.horoscopoapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: HoroscopeApi) {
    fun getHoroscope(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> = flow {
        try {
            val response = api.getHoroscope(horoscopeDTO.sign, horoscopeDTO.date, horoscopeDTO.lang)
            if (response.isSuccessful) {
                response.body()?.let { horoscopeResponse ->
                    emit(ResultType.Success(horoscopeResponse.toDomain()))
                }
            } else {
                val msg = when (response.code()) {
                    404 -> "Not Found"
                    405 -> "Mensaje 405"
                    else -> "Mensaje Genérico"
                }
                emit(ResultType.Error(msg))
            }
        } catch (e: Exception) {
            val msg = when(e) {
                is IOException -> "Error IO"
                else -> "Crash Final "
            }

            emit(ResultType.Error(msg))
        }
    }

}