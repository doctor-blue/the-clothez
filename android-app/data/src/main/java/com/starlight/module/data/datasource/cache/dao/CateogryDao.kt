package com.starlight.module.data.datasource.cache.dao

import androidx.room.*
import com.starlight.module.data.datasource.entity.CategoryEntity
import com.starlight.module.data.datasource.entity.SubCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("select * from category")
    fun getCategory(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Update
    suspend fun updateCategory(categoryEntity: CategoryEntity)

    @Delete
    suspend fun deleteCategory(categoryEntity: CategoryEntity)

    @Query("select * from subcategory")
    fun getSubCategory(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategory(subCategoryEntity: SubCategoryEntity)

    @Update
    suspend fun updateSubCategory(subCategoryEntity: SubCategoryEntity)

    @Delete
    suspend fun deleteSubCategory(subCategoryEntity: SubCategoryEntity)
}