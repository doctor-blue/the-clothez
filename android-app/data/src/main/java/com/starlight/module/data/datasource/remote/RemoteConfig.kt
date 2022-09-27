package com.starlight.module.data.datasource.remote

import com.starlight.module.data.datasource.remote.services.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteConfig {
    private const val BASE_URL = "http://192.168.22.3:3000"
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    val retrofit = builder.build()
}