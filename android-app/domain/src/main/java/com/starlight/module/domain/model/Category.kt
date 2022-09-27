package com.starlight.module.domain.model

data class Category(
    val id: String,
    val name: String,
    val gender: Int,
    val description: String
) {

}

data class SubCategory(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String
)