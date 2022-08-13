import DomainMapper from "src/domain/mappers/DomainMapper";
import { SubCategory } from "src/domain/model/Category";
import { SubCategoryEntity } from "../entity/SubCategoryEntity";

export class SubCategoryMapper implements DomainMapper<SubCategory, SubCategoryEntity>{
    toDomain(entity: SubCategoryEntity): SubCategory {
        return new SubCategory(
            entity.id,
            entity.category_id,
            entity.name,
            entity.description
        )
    }
    fromDomain(domain: SubCategory): SubCategoryEntity {
        return new SubCategoryEntity(
            domain.id,
            domain.categoryId,
            domain.name,
            domain.description
        )
    }


}