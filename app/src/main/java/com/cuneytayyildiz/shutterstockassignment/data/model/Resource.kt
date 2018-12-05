package com.cuneytayyildiz.shutterstockassignment.data.model


class Resource<T> private constructor(val status: Resource.Status, val data: T?, val exception: Throwable?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable?): Resource<T> {
            return Resource(Status.ERROR, null, exception)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}