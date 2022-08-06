package com.starlight.module.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

class IResponse<T>(
    @SerializedName("data")
    val data: T?,
    @SerializedName("status")
    val status: StatusResponse
)