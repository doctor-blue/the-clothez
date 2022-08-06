package com.starlight.module.data.datasource.entity

import com.google.gson.annotations.SerializedName

class TokenEntity(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)