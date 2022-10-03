package com.starlight.module.domain.model

class Product(
    val id: String,
    val name: String,
    val description: String,
    val productCode: String,
    val form: String,
    val material: String,
    val unit: String,
    val quantityPerUnit: Int,
    val price: Double,
    val unitPrice: String,
    val subCategoryId: String,
    var isFavorite: Boolean,
)