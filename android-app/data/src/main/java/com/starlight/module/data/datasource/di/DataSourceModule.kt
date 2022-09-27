package com.starlight.module.data.datasource.di

import android.app.Application
import androidx.room.Room
import com.starlight.module.data.datasource.cache.AuthCache
import com.starlight.module.data.datasource.cache.ClothezDatabase
import com.starlight.module.data.datasource.remote.RemoteConfig
import com.starlight.module.data.datasource.remote.services.AuthService
import com.starlight.module.data.datasource.remote.services.CategoryService
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
    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application,
        ClothezDatabase::class.java,
        ClothezDatabase.DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideAuthService(): AuthService = RemoteConfig.retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideCategoryService(): CategoryService =
        RemoteConfig.retrofit.create(CategoryService::class.java)


}