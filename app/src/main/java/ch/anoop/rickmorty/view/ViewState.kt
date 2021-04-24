package ch.anoop.rickmorty.view

sealed class ViewState<T>(
    val value: T? = null,
    val message: String? = null
) {
    class Loading<T> : ViewState<T>()
    class Empty<T>(data: T) : ViewState<T>(data)
    class Success<T>(data: T) : ViewState<T>(data)
    class Error<T>(message: String?, data: T? = null) : ViewState<T>(data, message)
}
