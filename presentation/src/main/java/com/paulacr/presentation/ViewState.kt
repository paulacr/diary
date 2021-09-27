package com.paulacr.presentation

sealed class ViewState<T> {
    data class Success<T>(val data: T?) : ViewState<T>()
    data class Error<T>(val data: T? = null, val exception: Exception) : ViewState<T>()
    data class Loading<T>(val data: T? = null) : ViewState<T>()
}
