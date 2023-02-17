package com.starlight.module.data.datasource.di

import com.starlight.module.data.datasource.cache.AuthCache
import com.starlight.module.data.datasource.cache.dao.CategoryDao
import com.starlight.module.data.datasource.cache.dao.ProductDao
import com.starlight.module.data.datasource.mappers.*
import com.starlight.module.data.datasource.remote.services.AuthService
import com.starlight.module.data.datasource.remote.RemoteConfig
import com.starlight.module.data.datasource.remote.services.CategoryService
import com.starlight.module.data.datasource.remote.services.ProductService
import com.starlight.module.data.repository.AuthenticationRepositoryImpl
import com.starlight.module.data.repository.CategoryRepositoryImpl
import com.starlight.module.data.repository.ProductRepositoryImpl
import com.starlight.module.domain.repository.AuthenticationRepository
import com.starlight.module.domain.repository.CategoryRepository
import com.starlight.module.domain.repository.ProductRepository
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

    @Provides
    @Singleton
    fun provideProductRepository(
        productDao: ProductDao,
        productService: ProductService,
        productMapper: ProductMapper,
        colorMapper: ProductColorMapper,
        sizeMapper: ProductSizeMapper,
    ): ProductRepository =
        ProductRepositoryImpl(productDao, productService, productMapper, colorMapper, sizeMapper)


}