import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { CartItemEntity } from 'src/data/entity/CartItemEntity';
import { CartController } from './cart.controller';
import { CartService } from './cart.service';

@Module({
    imports: [
        TypeOrmModule.forFeature([CartItemEntity]),
    ],
    controllers: [CartController],
    providers: [CartService]
})
export class CartModule {

}
