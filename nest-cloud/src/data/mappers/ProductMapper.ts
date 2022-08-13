import DomainMapper from "src/domain/mappers/DomainMapper";
import { Product } from "src/domain/model/Product";
import { ProductEntity } from "../entity/ProductEntity";

export class ProductMapper implements DomainMapper<Product, ProductEntity>{
    toDomain(entity: ProductEntity): Product {
        return new Product(
            entity.id,
            entity.name,
            entity.description,
            entity.product_code,
            entity.form,
            entity.material,
            entity.unit,
            entity.quantity_per_unit,
            entity.price,
            entity.unit_price,
            entity.sub_category_id,
            []
        )
    }

    fromDomain(domain: Product): ProductEntity {
        return new ProductEntity(
            domain.productId,
            domain.name,
            domain.description,
            domain.productCode,
            domain.form,
            domain.material,
            domain.unit,
            domain.quantityPerUnit,
            domain.price,
            domain.unitPrice,
            domain.subCategoryId,
            null,
            null
        )
    }

}