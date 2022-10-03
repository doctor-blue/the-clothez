package com.starlight.module.data.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.starlight.module.data.datasource.cache.dao.CategoryDao
import com.starlight.module.data.datasource.cache.dao.ProductDao
import com.starlight.module.data.datasource.entity.CategoryEntity
import com.starlight.module.data.datasource.entity.SubCategoryEntity

@Database(
    entities = [
        CategoryEntity::class,
        SubCategoryEntity::class
    ],
    version = ClothezDatabase.DB_VERSION
)
abstract class ClothezDatabase : RoomDatabase() {

    abstract val categoryDao: CategoryDao
    abstract val productDao: ProductDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "TheClothez"
    }
}