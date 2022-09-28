package com.starlight.module.domain.utils

import com.starlight.module.domain.model.Status

sealed class DataState<T>(
    var data: T? = null,
    var status: Status? = null
) {
    class Success<T>(data: T? = null, status: Status? = null) : DataState<T>(data = data, status) {
        override fun toString(): String {
            return "Success ${data.toString()}"
        }
    }

    class Loading<T>(data: T? = null, status: Status?=null) : DataState<T>(
        data, status
    ) {
        override fun toString(): String {
            return "Loading ${data.toString()}"
        }
    }

    class Error<T>(data: T? = null, status: Status? = null) :
        DataState<T>(data = data, status = status) {
        override fun toString(): String {
            return "Error ${status?.message}"
        }
    }

}










