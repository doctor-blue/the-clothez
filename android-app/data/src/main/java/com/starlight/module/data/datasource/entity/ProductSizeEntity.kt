package com.starlight.module.data.datasource.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

class ProductSizeEntity(
    @ColumnInfo(name = ID_COL)
    @SerializedName(ID_COL)
    val sizeId: String,
    @ColumnInfo(name = SIZE_COL)
    @SerializedName(SIZE_COL)
    val size: String
) {
    companion object {
        private const val ID_COL = "sizeId"
        private const val SIZE_COL = "size"
    }
}