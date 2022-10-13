package com.evenlab.dijisafe.base

sealed class BaseResponce<out T : Any> {
    class Success<out T : Any>(val data: T) : BaseResponce<T>()
    class Error(val exception: Throwable) : BaseResponce<Nothing>()
}