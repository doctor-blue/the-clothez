package com.starlight.module.domain.repository

import com.starlight.module.domain.model.Product
import com.starlight.module.domain.model.ProductInfo
import com.starlight.module.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getAllProduct(): Flow<DataState<List<Product>>>

    suspend fun getProductInfo(productId: String): Flow<DataState<ProductInfo>>

    suspend fun setFavorite(product: Product): Flow<DataState<Boolean>>

}