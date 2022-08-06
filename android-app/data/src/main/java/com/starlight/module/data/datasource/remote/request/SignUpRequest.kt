package com.starlight.module.data.datasource.remote.request

import com.google.gson.annotations.SerializedName
import com.starlight.module.data.datasource.entity.UserEntity

class SignUpRequest(
    @SerializedName("user_info")
    val userInfo:UserEntity,
    @SerializedName("password")
    val password:String
)