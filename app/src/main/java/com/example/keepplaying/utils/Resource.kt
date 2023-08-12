package com.example.keepplaying.utils


sealed class Resource <out R>{
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val message: String): Resource<Nothing>()
    data class ErrorConnection(val error: Exception): Resource<Nothing>()
    data class ErrorUi(val error: Exception): Resource<Nothing>()
    object Loading:Resource<Nothing>()
    object Finished:Resource<Nothing>()

}
fun<T> Resource<T>.mapToUiState(): UiState<T> {
    return when(this) {
        is Resource.Success -> UiState.Success(data = data)
        is Resource.ErrorUi -> UiState.Error(error = error)
        else -> throw IllegalStateException("Resource type not supported")
    }
}