package com.example.horoscopoapp.core.network

sealed class ResultType<T> {
    data class Success<T>(val data: T?) : ResultType<T>()
    data class Error<T>(val errorType: ErrorType) : ResultType<T>()
}