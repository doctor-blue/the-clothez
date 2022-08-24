import { Request, Body, Controller, Delete, Get, Param, Post, Put, UseFilters, UseGuards, Req } from '@nestjs/common';
import { CartItem } from 'src/domain/model/CartItem';
import { HttpExceptionFilter } from 'src/presentation/exception.filter';
import { JwtAuthGuard } from '../auth/local.authguard';
import { v1Path } from '../path';
import { CartService } from './cart.service';

@Controller(v1Path + 'cart')
export class CartController {

    constructor(
        private readonly cartService: CartService
    ) { }

    @Get()
    @UseGuards(JwtAuthGuard)
    @UseFilters(HttpExceptionFilter)
    async getCartItems(
        @Request() req
    ) {
        return this.cartService.getCartItems(req.user.userId);
    }

    @Post()
    @UseGuards(JwtAuthGuard)
    @UseFilters(HttpExceptionFilter)
    async addItemToCart(
        @Body()
        cartItem: CartItem,
        @Request()
        req
    ) {
        return this.cartService.addItemToCart(cartItem, req.user.userId);
    }

    @Put()
    @UseGuards(JwtAuthGuard)
    @UseFilters(HttpExceptionFilter)
    async updateItemToCart(
        @Body()
        cartItem: CartItem,
        @Request()
        req
    ) {
        return this.cartService.updateCartItem(cartItem, req.user.userId);
    }

    @Delete("\:id")
    @UseGuards(JwtAuthGuard)
    @UseFilters(HttpExceptionFilter)
    async deleteItem(
        @Param("id")
        itemId: string,
        @Request()
        req
    ) {
        return this.cartService.deleteItem(itemId);
    }


}
