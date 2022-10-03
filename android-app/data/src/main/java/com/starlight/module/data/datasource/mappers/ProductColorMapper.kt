package com.starlight.module.data.datasource.mappers

import com.starlight.module.data.datasource.entity.ProductColorEntity
import com.starlight.module.data.datasource.entity.ProductColorResEntity
import com.starlight.module.domain.mappers.DomainMapper
import com.starlight.module.domain.model.ProductColor
import javax.inject.Inject

class ProductColorMapper @Inject constructor(
    private val resMapper: ProductColorResMapper
) : DomainMapper<ProductColorEntity, ProductColor> {

    fun toDomain(
        entity: ProductColorEntity,
        productColorResEntities: List<ProductColorResEntity>
    ): ProductColor {
        val productColor = toDomain(entity)
        val resources = resMapper.toDomainList(productColorResEntities)
        productColor.res = resources
        return productColor
    }

    override fun toDomain(entity: ProductColorEntity): ProductColor {
        return ProductColor(
            entity.colorId,
            entity.productId,
            entity.name,
            entity.description,
            entity.hex,
            listOf()
        )
    }

    override fun fromDomain(model: ProductColor): ProductColorEntity {
        TODO("Not yet implemented")
    }
}