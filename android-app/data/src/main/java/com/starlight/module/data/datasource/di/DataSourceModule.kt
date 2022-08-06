package com.starlight.module.data.datasource.di

import android.app.Application
import com.starlight.module.data.datasource.cache.AuthCache
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
}