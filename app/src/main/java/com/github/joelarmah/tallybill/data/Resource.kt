package com.github.joelarmah.tallybill.data
sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}
