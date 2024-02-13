package com.jakurudev.pokedex4gen.domain.model

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val exception: Exception, val message: String? = exception.localizedMessage) : ApiResponse<Nothing>()
}
