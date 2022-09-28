package com.starlight.module.data.datasource.entity

import com.google.gson.annotations.SerializedName

class TokenEntity(
    @SerializedName("accessToken")
    var accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)