package com.starlight.module.domain.repository

import com.starlight.module.domain.model.Category
import com.starlight.module.domain.model.SubCategory
import com.starlight.module.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategoryByGender(gender: Int): Flow<DataState<List<Category>>>

    suspend fun getSubCategoryByCategoryId(categoryId: String): Flow<DataState<List<SubCategory>>>

    suspend fun refreshCategory(): Flow<DataState<List<Category>>>

}