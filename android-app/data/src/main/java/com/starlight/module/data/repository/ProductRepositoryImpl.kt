package com.starlight.module.data.repository

import android.provider.ContactsContract.Data
import com.starlight.module.data.datasource.cache.dao.ProductDao
import com.starlight.module.data.datasource.mappers.ProductColorMapper
import com.starlight.module.data.datasource.mappers.ProductMapper
import com.starlight.module.data.datasource.mappers.ProductSizeMapper
import com.starlight.module.data.datasource.remote.services.ProductService
import com.starlight.module.domain.model.Product
import com.starlight.module.domain.model.ProductInfo
import com.starlight.module.domain.repository.ProductRepository
import com.starlight.module.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    val productDao: ProductDao,
    val productService: ProductService,
    val productMapper: ProductMapper,
    val colorMapper: ProductColorMapper,
    val sizeMapper: ProductSizeMapper,
) : ProductRepository {

    override suspend fun getAllProduct(): Flow<DataState<List<Product>>> = flow {

    }

    override suspend fun getProductInfo(productId: String): Flow<DataState<ProductInfo>> = flow {

    }

    override suspend fun setFavorite(product: Product): Flow<DataState<Boolean>> = flow {

    }

}