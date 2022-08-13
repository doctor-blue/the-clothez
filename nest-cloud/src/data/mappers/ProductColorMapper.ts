import DomainMapper from "src/domain/mappers/DomainMapper";
import { ProductColor } from "src/domain/model/ProductColor";
import { ProductColorEntity } from "../entity/ProductColorEntity";

export class ProductColorMapper implements DomainMapper<ProductColor, ProductColorEntity>{
    toDomain(entity: ProductColorEntity): ProductColor {
        return new ProductColor(
            entity.id,
            entity.product_id,
            entity.name,
            entity.description,
            entity.color_hex,
            [],
            [],
        )
    }
    fromDomain(domain: ProductColor): ProductColorEntity {
        return new ProductColorEntity(
            domain.colorId,
            domain.productId,
            domain.name,
            domain.description,
            domain.hex,
            null,
            null
        )
    }

}