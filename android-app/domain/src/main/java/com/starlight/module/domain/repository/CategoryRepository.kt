package com.starlight.module.domain.repository

import com.starlight.module.domain.model.Category
import com.starlight.module.domain.model.SubCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategoryByGender(gender: Int): Flow<List<Category>>

    suspend fun getSubCategoryByCategoryId(categoryId: String): Flow<List<SubCategory>>

    suspend fun refreshCategory(): Flow<List<Category>>

}