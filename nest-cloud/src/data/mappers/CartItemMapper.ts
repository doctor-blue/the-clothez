import DomainMapper from "src/domain/mappers/DomainMapper";
import { CartItem } from "src/domain/model/CartItem";
import { CartItemEntity } from "../entity/CartItemEntity";

export class CartItemMapper implements DomainMapper<CartItem, CartItemEntity>{
    toDomain(entity: CartItemEntity): CartItem {

        return new CartItem(
            entity.id,
            entity.product_info_id,
            entity.user_id,
            entity.product_amount
        )
    }
    fromDomain(domain: CartItem): CartItemEntity {
        return new CartItemEntity(
            domain.id,
            domain.productInfoId,
            domain.userId,
            domain.productAmount
        )
    }

}