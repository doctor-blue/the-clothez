import DomainMapper from "src/domain/mappers/DomainMapper";
import { ProductInfo } from "src/domain/model/ProductInfo";
import { ProductInfoEntity } from "../entity/ProductInfoEntity";

export class ProductInfoMapper implements DomainMapper<ProductInfo, ProductInfoEntity>{
    toDomain(entity: ProductInfoEntity): ProductInfo {
        return new ProductInfo(
            entity.id,
            entity.size_id,
            entity.color_id,
            entity.product_amount
        )
    }
    fromDomain(domain: ProductInfo): ProductInfoEntity {
        return new ProductInfoEntity(
            domain.id,
            domain.sizeId,
            domain.colorId,
            domain.productAmount
        )
    }

}