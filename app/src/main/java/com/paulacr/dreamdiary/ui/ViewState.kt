package com.paulacr.dreamdiary.ui

sealed class ViewState<T> {
    class Success<T>(val data: T?) : ViewState<T>()
    class Error<T>(val data: T?, val exception: Exception) : ViewState<T>()
    class Loading<T>(val data: T? = null) : ViewState<T>()
}