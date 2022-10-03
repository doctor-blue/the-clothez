package com.starlight.module.data.datasource.di

import com.starlight.module.data.datasource.mappers.*
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

    @Provides
    @Singleton
    fun provideProductMapper(): ProductMapper = ProductMapper()

    @Provides
    @Singleton
    fun provideProductColorResMapper(): ProductColorResMapper = ProductColorResMapper()

    @Provides
    @Singleton
    fun provideProductColorMapper(
        resMapper: ProductColorResMapper
    ): ProductColorMapper = ProductColorMapper(resMapper)

    @Provides
    @Singleton
    fun provideProductSizeMapper(): ProductSizeMapper = ProductSizeMapper()


}