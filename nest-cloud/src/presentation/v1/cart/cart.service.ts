import { Injectable, InternalServerErrorException, UnauthorizedException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { CartItemEntity } from 'src/data/entity/CartItemEntity';
import MapperModule from 'src/di/MapperModule';
import { AUTHENTICATION_FAILURE, EXECUTE_QUERY_FAILURE, INVALID_INFO } from 'src/domain/const/ErrorConst';
import { SUCCESS_STATUS } from 'src/domain/const/StatusConst';
import { CartItem } from 'src/domain/model/CartItem';
import { IResponse } from 'src/presentation/response/IResponse';
import { Repository } from 'typeorm';

@Injectable()
export class CartService {

    constructor(
        @InjectRepository(CartItemEntity)
        private readonly cartRepo: Repository<CartItemEntity>,
    ) { }

    private cartItemMapper = MapperModule.getInstance().provideCartItemMapper()

    async getCartItems(
        userId: string
    ): Promise<IResponse<Array<CartItem>>> {
        console.log(userId);
        if (
            !userId
        ) {
            throw new InternalServerErrorException(INVALID_INFO.toJson());
        }
        let result: Array<CartItemEntity>;

        try {
            result = await this.cartRepo.findBy({ user_id: userId });
        } catch (err) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }
        // if (!result) {
        //     throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        // }
        const cartItems = []
        result.forEach(element => {
            cartItems.push(this.cartItemMapper.toDomain(element));
        })
        return new IResponse(cartItems, SUCCESS_STATUS)

    }

    async addItemToCart(
        cartItem: CartItem,
        userId: string
    ): Promise<IResponse<string>> {
        if (cartItem.userId !== userId) {
            throw new UnauthorizedException(AUTHENTICATION_FAILURE.toJson())
        }
        if (
            !cartItem.productInfoId ||
            !cartItem.userId ||
            !cartItem.productAmount
        ) {
            throw new InternalServerErrorException(INVALID_INFO.toJson());
        }
        let result;
        try {
            result = await this.cartRepo.createQueryBuilder().insert()
                .into(CartItemEntity).values([
                    {
                        product_info_id: cartItem.productInfoId,
                        user_id: cartItem.userId,
                        product_amount: cartItem.productAmount
                    }
                ]).returning("id").execute()
        } catch (err) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }
        if (!result) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(result.raw[0].id, SUCCESS_STATUS)
    }

    async updateCartItem(
        cartItem: CartItem,
        userId: string
    ): Promise<IResponse<boolean>> {
        if (cartItem.userId !== userId) {
            throw new UnauthorizedException(AUTHENTICATION_FAILURE.toJson())
        }
        if (
            !cartItem.id ||
            !cartItem.productAmount
        ) {
            throw new InternalServerErrorException(INVALID_INFO.toJson());
        }
        let result;
        try {
            result = await this.cartRepo.createQueryBuilder().update(CartItemEntity).set(
                {
                    product_amount: cartItem.productAmount
                }
            ).where("id=:id", { id: cartItem.id }).execute();
        } catch (err) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS)
    }

    async deleteItem(cartItemId: string): Promise<IResponse<boolean>> {
        if (
            !cartItemId
        )
            throw new InternalServerErrorException(INVALID_INFO.toJson());
        const result = await this.cartRepo.delete({
            id: cartItemId
        });


        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS)
    }

}
