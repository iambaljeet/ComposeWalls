package com.app.composewalls.model

sealed class ResultData<out T : Any> {
    class Loading<out T : Any> : ResultData<T>()
    data class Success<out T : Any>(val dataModel: T) : ResultData<T>()
    class NoData<out T : Any> : ResultData<T>()
    data class Failed(val message: String? = null, val exception: Exception? = null) :
        ResultData<Nothing>()
}