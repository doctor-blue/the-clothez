import DomainMapper from "src/domain/mappers/DomainMapper";
import { ProductSize } from "src/domain/model/ProductSize";
import { ProductSizeEntity } from "../entity/ProductSizeEntity";

export class ProductSizeMapper implements DomainMapper<ProductSize, ProductSizeEntity>{
    toDomain(entity: ProductSizeEntity): ProductSize {
        return new ProductSize(
            entity.id,
            entity.color_id,
            entity.size,
            entity.product_amount
        )
    }
    fromDomain(domain: ProductSize): ProductSizeEntity {
        return new ProductSizeEntity(
            domain.sizeId,
            domain.colorId,
            domain.size,
            domain.productAmount,
            null, null
        )
    }

}