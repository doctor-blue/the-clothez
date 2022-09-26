package com.starlight.module.data.datasource.di

import com.starlight.module.data.datasource.cache.AuthCache
import com.starlight.module.data.datasource.mappers.UserMapper
import com.starlight.module.data.datasource.remote.AuthService
import com.starlight.module.data.datasource.remote.RemoteConfig
import com.starlight.module.data.repository.AuthenticationRepositoryImpl
import com.starlight.module.domain.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthService(): AuthService = RemoteConfig.authService


    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
        userMapper: UserMapper,
        authCache: AuthCache,
    ): AuthenticationRepository = AuthenticationRepositoryImpl(authService, userMapper, authCache)
}