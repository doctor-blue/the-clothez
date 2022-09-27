package com.starlight.module.data.datasource.mappers

import com.starlight.module.data.datasource.entity.CategoryEntity
import com.starlight.module.data.datasource.entity.SubCategoryEntity
import com.starlight.module.domain.mappers.DomainMapper
import com.starlight.module.domain.model.Category
import com.starlight.module.domain.model.SubCategory

class CategoryMapper : DomainMapper<CategoryEntity, Category> {
    override fun toDomain(entity: CategoryEntity): Category {
        return Category(
            entity.id,
            entity.name,
            entity.gender,
            entity.description
        )
    }

    override fun fromDomain(model: Category): CategoryEntity {
        return CategoryEntity(
            model.id,
            model.name,
            model.gender,
            model.description
        )
    }
}

class SubCategoryMapper : DomainMapper<SubCategoryEntity, SubCategory> {
    override fun toDomain(entity: SubCategoryEntity): SubCategory {
        return SubCategory(
            entity.id,
            entity.categoryId,
            entity.name,
            entity.description
        )
    }

    override fun fromDomain(model: SubCategory): SubCategoryEntity {
        return SubCategoryEntity(
            model.id,
            model.categoryId,
            model.name,
            model.description
        )
    }
}