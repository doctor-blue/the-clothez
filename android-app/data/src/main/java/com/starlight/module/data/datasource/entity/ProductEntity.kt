package com.starlight.module.data.datasource.entity

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

open class ProductEntity(
    @SerializedName(PRODUCT_ID_COL)
    @ColumnInfo(name = PRODUCT_ID_COL)
    val productId: String,

    @SerializedName(NAME_COL)
    @ColumnInfo(name = NAME_COL)
    val name: String,

    @SerializedName(DESC_COL)
    @ColumnInfo(name = DESC_COL)
    val description: String,

    @SerializedName(PRODUCT_CODE_COL)
    @ColumnInfo(name = PRODUCT_CODE_COL)
    val productCode: String,

    @SerializedName(FORM_COL)
    @ColumnInfo(name = FORM_COL)
    val form: String,

    @SerializedName(MATERIAL_COL)
    @ColumnInfo(name = MATERIAL_COL)
    val material: String,

    @SerializedName(UNIT_COL)
    @ColumnInfo(name = UNIT_COL)
    val unit: String,

    @SerializedName(QUAN_PER_UNIT_COL)
    @ColumnInfo(name = QUAN_PER_UNIT_COL)
    val quantityPerUnit: Int,

    @SerializedName(PRICE_COL)
    @ColumnInfo(name = PRICE_COL)
    val price: Double,

    @SerializedName(UNIT_PRICE_COL)
    @ColumnInfo(name = UNIT_PRICE_COL)
    val unitPrice: String,

    @SerializedName(SUB_CATEGORY_ID_COL)
    @ColumnInfo(name = SUB_CATEGORY_ID_COL)
    val subCategoryId: String,
) {
    companion object {
        private const val PRODUCT_ID_COL = "productId"
        private const val NAME_COL = "name"
        private const val DESC_COL = "description"
        private const val PRODUCT_CODE_COL = "productCode"
        private const val FORM_COL = "form"
        private const val MATERIAL_COL = "material"
        private const val UNIT_COL = "unit"
        private const val QUAN_PER_UNIT_COL = "quantityPerUnit"
        private const val PRICE_COL = "price"
        private const val UNIT_PRICE_COL = "unitPrice"
        private const val SUB_CATEGORY_ID_COL = "subCategoryId"
    }
}

class ProductDtoEntity(
    productId: String,
    name: String,
    description: String,
    productCode: String,
    form: String,
    material: String,
    unit: String,
    quantityPerUnit: Int,
    price: Double,
    unitPrice: String,
    subCategoryId: String,
    @SerializedName(COLORS_COL)
    @ColumnInfo(name = COLORS_COL)
    val colors: List<ProductColorEntity>,
    @SerializedName(SIZES_COL)
    @ColumnInfo(name = SIZES_COL)
    val sizes: List<ProductSizeEntity>,
    @SerializedName(INFOS_COL)
    @ColumnInfo(name = INFOS_COL)
    val productInfo: List<ProductInfoEntity>
) : ProductEntity(
    productId,
    name,
    description,
    productCode,
    form,
    material,
    unit,
    quantityPerUnit,
    price,
    unitPrice,
    subCategoryId
) {
    companion object {
        private const val COLORS_COL = "colors"
        private const val SIZES_COL = "sizes"
        private const val INFOS_COL = "subCategoryId"
    }
}