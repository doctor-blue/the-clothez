import { Body, Catch, Controller, Delete, Get, Param, Post, Put, UseFilters, UseGuards } from '@nestjs/common';
import { Category, SubCategory } from 'src/domain/model/Category';
import { HttpExceptionFilter } from 'src/presentation/exception.filter';
import { JwtAuthGuard } from '../auth/local.authguard';
import { v1Path } from '../path';
import { CategoryService } from './category.service';

@Controller(v1Path + 'category')
export class CategoryController {

    constructor(
        private readonly categoryService: CategoryService
    ) { }


    @Post()
    @UseGuards(JwtAuthGuard)
    @UseFilters(HttpExceptionFilter)
    async createCategory(
        @Body() category: Category
    ) {
        return await this.categoryService.cretateCategory(category)
    }

    @Delete("/:id")
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async deleteCategory(
        @Param("id") categoryId: string
    ) {
        return await this.categoryService.deleteCategory(categoryId);
    }

    @Put()
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async updateCategory(
        @Body() category: Category
    ) {
        return await this.categoryService.updateCategory(category);
    }

    @Get()
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async getCategory() {
        return await this.categoryService.getCategory();
    }

    @Post('sub')
    @UseGuards(JwtAuthGuard)
    @UseFilters(HttpExceptionFilter)
    async createSubCategory(
        @Body() subCategory: SubCategory
    ) {
        return await this.categoryService.cretateSubCategory(subCategory);
    }

    @Delete('sub/:id')
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async deleteSubCategory(
        @Param("id")
        categoryId: string
    ) {
        return await this.categoryService.deleteSubCategory(categoryId);
    }

    @Put('sub')
    @UseFilters(HttpExceptionFilter)
    @UseGuards(JwtAuthGuard)
    async updateSubCategory(
        @Body()
        subCategory: SubCategory
    ) {
        return await this.categoryService.updateSubCategory(subCategory);
    }



}
