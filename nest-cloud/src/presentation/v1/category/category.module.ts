import { Module } from '@nestjs/common';
import { CategoryService } from './category.service';
import { CategoryController } from './category.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { CategoryEntity } from 'src/data/entity/CategoryEntity';
import { SubCategoryEntity } from 'src/data/entity/SubCategoryEntity';

@Module({
  imports: [
    TypeOrmModule.forFeature([CategoryEntity, SubCategoryEntity]),
  ],
  providers: [CategoryService],
  controllers: [CategoryController]
})
export class CategoryModule { }
