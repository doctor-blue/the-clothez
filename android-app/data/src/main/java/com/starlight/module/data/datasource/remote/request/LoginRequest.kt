package com.starlight.module.data.datasource.remote.request

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("user_name")
    val email: String,
    @SerializedName("password")
    val password: String
)