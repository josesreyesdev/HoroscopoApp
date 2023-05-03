package com.example.horoscopoapp.repository

import com.example.horoscopoapp.core.network.ErrorType
import com.example.horoscopoapp.core.network.ResultType
import com.example.horoscopoapp.data.BaseRepository
import com.example.horoscopoapp.data.network.HoroscopeApi
import com.example.horoscopoapp.data.network.model.toDomain
import com.example.horoscopoapp.domain.dto.HoroscopeDTO
import com.example.horoscopoapp.domain.model.HoroscopeModel
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: HoroscopeApi): BaseRepository() {

    /* Coroutine Basico */
    suspend fun getHoroscope( horoscopeDTO: HoroscopeDTO) : ResultType<HoroscopeModel> {
        return try {
            val response = api.getHoroscope(horoscopeDTO.sign, horoscopeDTO.date, horoscopeDTO.lang)
            if (response.isSuccessful) {
                response.body()?.let { horoscopeResponse ->
                    ResultType.Success(horoscopeResponse.toDomain())
                } ?: ResultType.Error(ErrorType.UncontrolledError(code = 999))
            } else {
                val msg = when (response.code()) {
                    ErrorType.BadRequest.errorCode -> ErrorType.BadRequest
                    ErrorType.InvalidData.errorCode -> ErrorType.InvalidData
                    ErrorType.Forbidden.errorCode -> ErrorType.Forbidden
                    ErrorType.InternalServerError.errorCode -> ErrorType.InternalServerError
                    else -> ErrorType.UncontrolledError(response.code())
                }
                ResultType.Error(msg)
            }
        } catch (ex: Exception) {
            ResultType.Error(ErrorType.ExceptionError(exception = ex))
        }
    }

    /* Flow avanzado*/
    /*fun getHoroscope(horoscopeDTO: HoroscopeDTO) = runApiCallFlow {
        //getHosroscope2 no debe ser una funcion suspend
        api.getHoroscope2(horoscopeDTO.sign, horoscopeDTO.date, horoscopeDTO.lang)
    } */

    /* Flow Básico */
    /*fun getHoroscope(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> = flow {
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
    } */
}