package com.starlight.module.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class StatusResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
)