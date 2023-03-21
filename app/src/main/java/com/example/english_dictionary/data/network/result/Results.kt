package com.example.english_dictionary.data.network.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Results<out T> {
    object Loading: Results<Nothing>()
    data class Success<T>(val data: T): Results<T>()
    data class Error(val throwable: Throwable): Results<Nothing>()
}


