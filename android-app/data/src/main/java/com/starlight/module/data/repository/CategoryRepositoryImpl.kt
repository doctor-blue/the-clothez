package com.starlight.module.data.repository

import com.starlight.module.data.datasource.mappers.CategoryMapper
import com.starlight.module.data.datasource.mappers.SubCategoryMapper
import com.starlight.module.data.datasource.remote.services.CategoryService
import com.starlight.module.domain.model.Category
import com.starlight.module.domain.model.SubCategory
import com.starlight.module.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl(
    val categoryService: CategoryService,
    val categoryMapper: CategoryMapper,
    val subCategoryMapper: SubCategoryMapper
) : CategoryRepository {

    override suspend fun getCategoryByGender(gender: Int): Flow<List<Category>> = flow {

    }

    override suspend fun getSubCategoryByCategoryId(categoryId: String): Flow<List<SubCategory>> =
        flow {

        }

    override suspend fun refreshCategory(): Flow<List<Category>> = flow {

    }

}