package com.starlight.module.data.datasource.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

open class ProductColorEntity(
    @ColumnInfo(name = ID_COL)
    @SerializedName(ID_COL)
    val colorId: String,
    @ColumnInfo(name = PRODUCT_ID_COL)
    @SerializedName(PRODUCT_ID_COL)
    val productId: String,
    @ColumnInfo(name = NAME_COL)
    @SerializedName(NAME_COL)
    val name: String,
    @ColumnInfo(name = DESC_COL)
    @SerializedName(DESC_COL)
    val description: String,
    @ColumnInfo(name = HEX_COL)
    @SerializedName(HEX_COL)
    val hex: String,
) {
    companion object {
        private const val ID_COL = "colorId"
        private const val NAME_COL = "name"
        private const val PRODUCT_ID_COL = "productId"
        private const val DESC_COL = "description"
        private const val HEX_COL = "hex"
    }
}

class ProductColorDtoEntity(
    colorId: String,
    productId: String,
    name: String,
    description: String,
    hex: String,
    @ColumnInfo(name = RES_COL)
    @SerializedName(RES_COL)
    val res: List<ProductColorResEntity>
) : ProductColorEntity(colorId, productId, name, description, hex) {
    companion object {
        private const val RES_COL = "resources"
    }
}