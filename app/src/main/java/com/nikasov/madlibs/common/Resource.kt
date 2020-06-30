package com.nikasov.madlibs.common


sealed class Resource<T> (
    val data : T? = null
) {
    class InProgress<T>(data : T) : Resource<T>(data)
    class Success<T> : Resource<T>()
}