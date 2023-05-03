package com.example.horoscopoapp.data

import com.example.horoscopoapp.core.network.ErrorType
import com.example.horoscopoapp.core.network.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class BaseRepository {

    inline fun <T>runApiCallFlow(crossinline call: () -> Response<T>): Flow<ResultType<T>> = flow {

        try {
            val response = call()
            if (response.isSuccessful) {
                emit(ResultType.Success(data = response.body()))
            } else {
                val error = when(response.code()) {
                    ErrorType.BadRequest.errorCode -> ErrorType.BadRequest
                    ErrorType.InvalidData.errorCode -> ErrorType.InvalidData
                    ErrorType.Forbidden.errorCode -> ErrorType.Forbidden
                    ErrorType.InternalServerError.errorCode -> ErrorType.InternalServerError
                    else -> ErrorType.UncontrolledError(response.code())
                }
                emit(ResultType.Error(errorType = error))
            }
        } catch (ex: Exception) {
            emit(ResultType.Error(ErrorType.ExceptionError(exception = ex)))
        }
    }
}