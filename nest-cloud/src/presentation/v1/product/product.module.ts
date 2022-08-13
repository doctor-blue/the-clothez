import { Module } from '@nestjs/common';
import { ProductService } from './product.service';
import { ProductController } from './product.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ProductEntity } from 'src/data/entity/ProductEntity';
import { ProductColorEntity } from 'src/data/entity/ProductColorEntity';
import { ProductSizeEntity } from 'src/data/entity/ProductSizeEntity';
import { ProductColorResEntity } from 'src/data/entity/ProductColorResEntity';

@Module({
  imports: [
    TypeOrmModule.forFeature([
      ProductEntity,
      ProductColorEntity,
      ProductSizeEntity,
      ProductColorResEntity
    ]),
  ],
  providers: [ProductService],
  controllers: [ProductController]
})
export class ProductModule { }
