package com.starlight.module.data.datasource.remote.request

import com.google.gson.annotations.SerializedName

class RefreshTokenRequest(
    @SerializedName("refresh_token")
    val refreshToken: String
)