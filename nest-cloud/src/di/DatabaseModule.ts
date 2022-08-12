

import { TypeOrmModule } from '@nestjs/typeorm';
import { CategoryEntity } from 'src/data/entity/CategoryEntity';
import { PermissionEntity } from 'src/data/entity/PermissionEntity';
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
        entities: [UserEntity, PermissionEntity, CategoryEntity, SubCategoryEntity],
        synchronize: true,
    }
);

export const DatabaseFeature = TypeOrmModule.forFeature([
    UserEntity, PermissionEntity
])