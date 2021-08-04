package dev.kibet.movetechtest.others

data class Resource<out T>(val status: String, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = "", data = data, message = null)

        fun <T> error(data: T?, message: String?): Resource<T> =
            Resource(status = "", data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = "", data = data, message = null)
    }
}

enum class Status {
    SUCCESS,ERROR,LOADING
}