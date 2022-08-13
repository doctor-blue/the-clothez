

import { TypeOrmModule } from '@nestjs/typeorm';
import { CategoryEntity } from 'src/data/entity/CategoryEntity';
import { PermissionEntity } from 'src/data/entity/PermissionEntity';
import { ProductColorEntity } from 'src/data/entity/ProductColorEntity';
import { ProductColorResEntity } from 'src/data/entity/ProductColorResEntity';
import { ProductEntity } from 'src/data/entity/ProductEntity';
import { ProductSizeEntity } from 'src/data/entity/ProductSizeEntity';
import { SubCategoryEntity } from 'src/data/entity/SubCategoryEntity';
import { UserEntity } from 'src/data/entity/UserEntity';

export const DatabaseModule = TypeOrmModule.forRoot(
    {
        type: 'postgres',
        port: Number(process.env.DB_PORT),
        host: process.env.DB_HOST,
        username: process.env.DB_USER,
        password: process.env.DB_PWD,
        database: 'theclothez',
        entities: [
            UserEntity,
            PermissionEntity,
            CategoryEntity,
            SubCategoryEntity,
            ProductEntity,
            ProductColorEntity,
            ProductColorResEntity,
            ProductSizeEntity
        ],
        synchronize: true,
    }
);

export const DatabaseFeature = TypeOrmModule.forFeature([
    UserEntity, PermissionEntity
])