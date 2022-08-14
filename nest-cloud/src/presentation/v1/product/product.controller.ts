import { Body, Controller, Delete, Get, Param, Post, Put, UseFilters, UseGuards } from '@nestjs/common';
import { Product } from 'src/domain/model/Product';
import { ProductColor } from 'src/domain/model/ProductColor';
import { ProductColorRes } from 'src/domain/model/ProductColorRes';
import { ProductSize } from 'src/domain/model/ProductSize';
import { HttpExceptionFilter } from 'src/presentation/exception.filter';
import { JwtAuthGuard } from '../auth/local.authguard';
import { v1Path } from '../path';
import { ProductService } from './product.service';

@Controller(v1Path + 'product')
export class ProductController {

    constructor(
        private readonly productService: ProductService
    ) { }

    @Get()
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async getProducts() {
        return await this.productService.getProducts();
    }

    @Post()
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async createProduct(
        @Body()
        product: Product
    ) {
        return await this.productService.createProduct(product)
    }

    @Put()
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async updateProduct(
        @Body()
        product: Product
    ) {
        return await this.productService.updateProduct(product);
    }

    @Delete("/:id")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async deleteProduct(
        @Param("id")
        productId: string
    ) {
        return await this.productService.deleteProduct(productId);
    }



    @Post("/color")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async createProductColor(
        @Body()
        color: ProductColor
    ) {
        return await this.productService.createProductColor(color)

    }
    @Put("/color")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async updateProductColor(
        @Body()
        color: ProductColor
    ) {
        return await this.productService.updateProductColor(color);
    }

    @Delete("color/:id")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async deleteProductColor(
        @Param("id")
        colorId: string
    ) {
        return await this.productService.deleteProductColor(colorId);
    }

    @Post("/size")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async createProductSize(
        @Body()
        size: ProductSize
    ) {
        return await this.productService.createProductSize(size);
    }

    @Put("/size")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async updateProductSize(
        @Body()
        size: ProductSize
    ) {
        return await this.productService.updateProductSize(size);
    }

    @Delete("/size/:id")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async deleteProductSize(
        @Param("id")
        sizeId: string
    ) {
        return await this.productService.deleteProductSize(sizeId);
    }

    @Post("/res")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async createProductRes(
        @Body()
        res: ProductColorRes
    ) {
        return await this.productService.createProductColorRes(res);
    }

    @Put("/res")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async updateProductRes(
        @Body()
        res: ProductColorRes
    ) {
        return await this.productService.updateProductColorRes(res);
    }

    @Delete("/res/:id")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async deleteProductRes(
        @Param("id")
        resId: string
    ) {
        return await this.productService.deleteProductColorRes(resId);
    }


}
