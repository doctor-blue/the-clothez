package com.starlight.module.data.datasource.entity

import com.google.gson.annotations.SerializedName

class UserEntity(
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("is_active")
    val isActive: String,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("dob")
    val dob: String?,
    @SerializedName("permission_type")
    val permissionType: Int
)