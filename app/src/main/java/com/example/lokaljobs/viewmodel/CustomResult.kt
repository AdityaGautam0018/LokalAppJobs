package com.example.lokaljobs.viewmodel

sealed class CustomResult<out T> {
    data class Success<out T>(val data: T) : CustomResult<T>()
    data class Failure(val exception: Throwable) : CustomResult<Nothing>()
}