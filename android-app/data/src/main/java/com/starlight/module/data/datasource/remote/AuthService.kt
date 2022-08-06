package com.starlight.module.data.datasource.remote

import com.starlight.module.data.datasource.entity.TokenEntity
import com.starlight.module.data.datasource.remote.request.LoginRequest
import com.starlight.module.data.datasource.remote.request.RefreshTokenRequest
import com.starlight.module.data.datasource.remote.request.SignUpRequest
import com.starlight.module.data.datasource.remote.response.IResponse
import com.starlight.module.data.datasource.remote.response.StatusResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService : IClothezService {

    @POST("/api/v1/auth/signIn")
    suspend fun login(@Body request: LoginRequest): IResponse<TokenEntity>

    @POST("/api/v1/auth/signUp")
    suspend fun signUp(@Body request: SignUpRequest): IResponse<Any>

    @POST("/api/v1/auth/forgot")
    suspend fun forgotPassword(): IResponse<Any>

    @POST("/api/v1/auth/refresh_token")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): IResponse<TokenEntity>
}