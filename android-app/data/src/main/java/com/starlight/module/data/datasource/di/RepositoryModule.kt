package com.starlight.module.data.datasource.di

import com.starlight.module.data.datasource.cache.AuthCache
import com.starlight.module.data.datasource.cache.dao.CategoryDao
import com.starlight.module.data.datasource.mappers.CategoryMapper
import com.starlight.module.data.datasource.mappers.SubCategoryMapper
import com.starlight.module.data.datasource.mappers.UserMapper
import com.starlight.module.data.datasource.remote.services.AuthService
import com.starlight.module.data.datasource.remote.RemoteConfig
import com.starlight.module.data.datasource.remote.services.CategoryService
import com.starlight.module.data.repository.AuthenticationRepositoryImpl
import com.starlight.module.data.repository.CategoryRepositoryImpl
import com.starlight.module.domain.repository.AuthenticationRepository
import com.starlight.module.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
        userMapper: UserMapper,
        authCache: AuthCache,
    ): AuthenticationRepository = AuthenticationRepositoryImpl(authService, userMapper, authCache)


    @Provides
    @Singleton
    fun provideCategoryRepository(
        authCache: AuthCache,
        categoryService: CategoryService,
        categoryMapper: CategoryMapper,
        categoryDao: CategoryDao,
        subCategoryMapper: SubCategoryMapper
    ): CategoryRepository =
        CategoryRepositoryImpl(
            authCache,
            categoryService,
            categoryMapper,
            categoryDao,
            subCategoryMapper
        )


}