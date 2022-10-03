package com.starlight.module.data.datasource.di

import android.app.Application
import androidx.room.Room
import com.starlight.module.data.datasource.cache.AuthCache
import com.starlight.module.data.datasource.cache.ClothezDatabase
import com.starlight.module.data.datasource.cache.dao.CategoryDao
import com.starlight.module.data.datasource.cache.dao.ProductDao
import com.starlight.module.data.datasource.remote.RemoteConfig
import com.starlight.module.data.datasource.remote.services.AuthService
import com.starlight.module.data.datasource.remote.services.CategoryService
import com.starlight.module.data.datasource.remote.services.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideAuthCache(application: Application): AuthCache = AuthCache(application)

    @Provides
    @Singleton
    fun provideDatabase(application: Application): ClothezDatabase = Room.databaseBuilder(
        application,
        ClothezDatabase::class.java,
        ClothezDatabase.DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideCategoryDao(clothezDatabase: ClothezDatabase): CategoryDao =
        clothezDatabase.categoryDao

    @Provides
    @Singleton
    fun provideProductDao(clothezDatabase: ClothezDatabase): ProductDao =
        clothezDatabase.productDao

    @Provides
    @Singleton
    fun provideAuthService(): AuthService = RemoteConfig.retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideCategoryService(): CategoryService =
        RemoteConfig.retrofit.create(CategoryService::class.java)

    @Provides
    @Singleton
    fun provideProductService(): ProductService =
        RemoteConfig.retrofit.create(ProductService::class.java)

}