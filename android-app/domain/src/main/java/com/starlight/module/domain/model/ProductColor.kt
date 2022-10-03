package com.starlight.module.domain.model

class ProductColor(
    val id: String,
    val productId: String,
    val name: String,
    val description: String,
    val hex: String,
    var res: List<ProductResource>
)