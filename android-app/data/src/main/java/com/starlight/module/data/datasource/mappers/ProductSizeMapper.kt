package com.starlight.module.data.datasource.mappers

import com.starlight.module.data.datasource.entity.ProductSizeEntity
import com.starlight.module.domain.mappers.DomainMapper
import com.starlight.module.domain.model.ProductSize

class ProductSizeMapper : DomainMapper<ProductSizeEntity, ProductSize> {
    override fun toDomain(entity: ProductSizeEntity): ProductSize {
        return ProductSize(
            entity.sizeId,
            entity.size
        )
    }

    override fun fromDomain(model: ProductSize): ProductSizeEntity {
        return ProductSizeEntity(
            model.id,
            model.size
        )
    }
}