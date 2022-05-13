package com.example.tmdb_17hw.data

sealed class Result<T> {
    class Success<T>(val data: T?) : Result<T>()
    class Loading<T>(val data: T? = null) : Result<T>()
    class Error<T>(val error: Throwable) : Result<T>()

    val isSuccessful: Boolean = this is Success<T>

    companion object {
        fun <T> loading(data: T? = null) = Loading(data)
        fun <T> success(data: T?) = Success(data)
        fun error(error: Throwable) = Error<Unit>(error)
    }
}