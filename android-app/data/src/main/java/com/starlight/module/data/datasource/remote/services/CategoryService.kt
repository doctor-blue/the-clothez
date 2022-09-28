package com.starlight.module.data.datasource.remote.services

import com.starlight.module.data.datasource.entity.CategoriesDto
import com.starlight.module.data.datasource.remote.IClothezService
import com.starlight.module.data.datasource.remote.request.RequestHeader
import com.starlight.module.data.datasource.remote.response.IResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CategoryService : IClothezService {

    @GET("/api/v1/category/")
    suspend fun getCategoryDto(@Header(RequestHeader.AUTHORIZATION) accessToken: String): IResponse<CategoriesDto>
}