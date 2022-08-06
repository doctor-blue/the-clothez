package com.starlight.module.domain.model

import java.util.*

data class User(
    val userName: String,
    val userId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val isActive: String,
    val avatar: String,
    val gender: Int,
    val dob: Date,
    val permissionType: Int
) {
    companion object {
        const val NORMAL_USER = 0
        const val ADMIN = 1
        const val SUPER_ADMIN = 2
    }
}