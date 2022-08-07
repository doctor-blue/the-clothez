import { LOCALE_DEFAULT } from "../../domain/const/Locale";
import DomainMapper from "../../domain/mappers/DomainMapper";
import { Category } from "../../domain/model/Category";
import { CategoryEntity } from "../entity/CategoryEntity";

export class CategoryMapper implements DomainMapper<Category, CategoryEntity>{
    toDomain(entity: CategoryEntity): Category {
        return new Category(
            entity.id,
            entity.name,
            entity.gender,
            entity.description,
            entity.lang,
        )
    }
    fromDomain(domain: Category): CategoryEntity {
        return new CategoryEntity(
            domain.id,
            domain.name,
            domain.description,
            domain.gender,
            !domain.lang ? LOCALE_DEFAULT : domain.lang
        )
    }
}