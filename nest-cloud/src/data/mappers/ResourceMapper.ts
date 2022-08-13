import DomainMapper from "src/domain/mappers/DomainMapper";
import { ProductColorRes } from "src/domain/model/ProductColorRes";
import { ProductColorResEntity } from "../entity/ProductColorResEntity";

export class ProductColorResMapper implements DomainMapper<ProductColorRes, ProductColorResEntity>{
    toDomain(entity: ProductColorResEntity): ProductColorRes {
        return new ProductColorRes(
            entity.id,
            entity.color_id,
            entity.url,
            entity.description,
            entity.res_type,
            entity.mine_type
        )
    }
    fromDomain(domain: ProductColorRes): ProductColorResEntity {
        return new ProductColorResEntity(
            domain.resId,
            domain.colorId,
            domain.url,
            domain.description,
            domain.resType,
            domain.mineType,
            null,
            null
        )
    }

}