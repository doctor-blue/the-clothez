package com.starlight.module.data.datasource.mappers

import com.starlight.module.data.datasource.entity.ProductEntity
import com.starlight.module.domain.mappers.DomainMapper
import com.starlight.module.domain.model.Product


class ProductMapper : DomainMapper<ProductEntity, Product> {
    override fun toDomain(entity: ProductEntity): Product {
        return Product(
            entity.productId,
            entity.name,
            entity.description,
            entity.productCode,
            entity.form,
            entity.material,
            entity.unit,
            entity.quantityPerUnit,
            entity.price,
            entity.unitPrice,
            entity.subCategoryId,
            false,
        )
    }

    fun toDomain(entity: ProductEntity, isFavorite: Boolean): Product {
        val product = toDomain(entity)
        product.isFavorite = isFavorite
        return product
    }

    override fun fromDomain(model: Product): ProductEntity {
        return ProductEntity(
            model.id,
            model.name,
            model.description,
            model.productCode,
            model.form,
            model.material,
            model.unit,
            model.quantityPerUnit,
            model.price,
            model.unitPrice,
            model.subCategoryId
        )
    }
}