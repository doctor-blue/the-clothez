package com.starlight.module.data.datasource.remote.response

import com.google.gson.annotations.SerializedName
import com.starlight.module.domain.model.Status

data class StatusResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String

) {
    fun toStatusModel() = Status(code, message)
}