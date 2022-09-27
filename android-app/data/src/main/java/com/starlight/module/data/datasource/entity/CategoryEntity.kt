package com.starlight.module.data.datasource.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = ID_COL)
    @SerializedName(ID_COL)
    val id: String,

    @ColumnInfo(name = NAME_COL)
    @SerializedName(NAME_COL)
    val name: String,

    @ColumnInfo(name = GENDER_COL)
    @SerializedName(GENDER_COL)
    val gender: Int,

    @ColumnInfo(name = DESC_COL)
    @SerializedName(DESC_COL)
    val description: String,
) {
    companion object {
        private const val ID_COL = "id"
        private const val NAME_COL = "name"
        private const val GENDER_COL = "gender"
        private const val DESC_COL = "description"
    }
}

@Entity(tableName = "SubCategory")
class SubCategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = ID_COL)
    @SerializedName(ID_COL)
    val id: String,

    @ColumnInfo(name = CATEGORY_ID_COL)
    @SerializedName(CATEGORY_ID_COL)
    val categoryId: String,

    @ColumnInfo(name = NAME_COL)
    @SerializedName(NAME_COL)
    val name: String,

    @ColumnInfo(name = DESC_COL)
    @SerializedName(DESC_COL)
    val description: String,

) {
    companion object {
        private const val ID_COL = "id"
        private const val NAME_COL = "name"
        private const val CATEGORY_ID_COL = "categoryId"
        private const val DESC_COL = "description"

    }
}


class CategoriesDto(
    @SerializedName("categories")
    val categories: List<CategoryEntity>,
    @SerializedName("subCategories")
    val subCategories: List<SubCategoryEntity>,
)