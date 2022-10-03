package com.starlight.module.data.datasource.mappers

import com.starlight.module.data.datasource.entity.ProductColorEntity
import com.starlight.module.data.datasource.entity.ProductColorResEntity
import com.starlight.module.domain.mappers.DomainMapper
import com.starlight.module.domain.model.ProductResource

class ProductColorResMapper : DomainMapper<ProductColorResEntity, ProductResource> {
    override fun toDomain(entity: ProductColorResEntity): ProductResource {
        return ProductResource(
            entity.id,
            entity.colorId,
            entity.url,
            entity.description,
            entity.resType,
            entity.mineType
        )
    }

    override fun fromDomain(model: ProductResource): ProductColorResEntity {
        return ProductColorResEntity(
            model.id,
            model.colorId,
            model.url,
            model.description,
            model.resType,
            model.mineType
        )
    }
}