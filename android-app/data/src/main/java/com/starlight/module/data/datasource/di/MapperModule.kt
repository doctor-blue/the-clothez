package com.starlight.module.data.datasource.di

import com.starlight.module.data.datasource.mappers.CategoryMapper
import com.starlight.module.data.datasource.mappers.SubCategoryMapper
import com.starlight.module.data.datasource.mappers.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {


    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper = UserMapper()

    @Provides
    @Singleton
    fun provideCategoryMapper(): CategoryMapper = CategoryMapper()

    @Provides
    @Singleton
    fun provideSubCategoryMapper(): SubCategoryMapper = SubCategoryMapper()


}