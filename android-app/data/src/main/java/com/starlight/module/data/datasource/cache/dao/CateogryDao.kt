package com.starlight.module.data.datasource.cache.dao

import androidx.room.*
import com.starlight.module.data.datasource.entity.CategoryEntity
import com.starlight.module.data.datasource.entity.SubCategoryEntity
import com.starlight.module.domain.model.SubCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("select * from category")
    fun getCategory(): Flow<List<CategoryEntity>>

    @Query("select * from category where gender=:gender")
    fun getCategoryByGender(gender: Int): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Update
    suspend fun updateCategory(categoryEntity: CategoryEntity)

    @Delete
    suspend fun deleteCategory(categoryEntity: CategoryEntity)

    @Query("select * from subcategory")
    fun getSubCategory(): Flow<List<SubCategoryEntity>>

    @Query("select * from subcategory where categoryId=:categoryId")
    fun getSubCategoryByCategory(categoryId: String): Flow<List<SubCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategory(subCategoryEntity: SubCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategories(subCategories: List<SubCategoryEntity>)

    @Update
    suspend fun updateSubCategory(subCategoryEntity: SubCategoryEntity)

    @Delete
    suspend fun deleteSubCategory(subCategoryEntity: SubCategoryEntity)
}