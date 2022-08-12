import { Injectable, InternalServerErrorException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { CategoryEntity } from 'src/data/entity/CategoryEntity';
import { SubCategoryEntity } from 'src/data/entity/SubCategoryEntity';
import MapperModule from 'src/di/MapperModule';
import { CREATE_CATEGORY_FAILURE, DELETE_CATEGORY_FAILURE } from 'src/domain/const/ErrorConst';
import { SUCCESS_STATUS } from 'src/domain/const/StatusConst';
import { Category } from 'src/domain/model/Category';
import { currentTime } from 'src/domain/utils/Time';
import { IResponse } from 'src/presentation/response/IResponse';
import { Repository } from 'typeorm';

@Injectable()
export class CategoryService {

    constructor(
        @InjectRepository(CategoryEntity)
        private readonly categoryRepo: Repository<CategoryEntity>,
        @InjectRepository(SubCategoryEntity)
        private readonly subCategoryRepo: Repository<SubCategoryEntity>
    ) { }

    private categoryMapper = MapperModule.getInstance().provideCategoryMapper();

    async cretateCategory(category: Category): Promise<IResponse<string>> {
        const result = await this.categoryRepo.createQueryBuilder().insert().into(CategoryEntity).values([
            {
                gender: category.gender,
                name: category.name,
                description: category.description
            }
        ]).returning("id").execute()

        if (!result) {
            throw new InternalServerErrorException(CREATE_CATEGORY_FAILURE.toJson());
        }

        return new IResponse(result.raw[0].id, SUCCESS_STATUS)
    }

    async updateCategory(category: Category): Promise<IResponse<boolean>> {
        console.log(category);

        const result = await this.categoryRepo.createQueryBuilder()
            .update(CategoryEntity).set({
                updated_at: currentTime(),
                gender: category.gender,
                name: category.name,
                description: category.description
            }).where("id=:id", { id: category.id }).execute();
        console.log(result);

        if (result.affected == 0) {
            throw new InternalServerErrorException(CREATE_CATEGORY_FAILURE.toJson());
        }
        return new IResponse(true, SUCCESS_STATUS)
    }

    async deleteCategory(categoryId: string): Promise<IResponse<boolean>> {

        const result = await this.categoryRepo.delete({
            id: categoryId
        });


        if (result.affected == 0) {
            throw new InternalServerErrorException(DELETE_CATEGORY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS)
    }



}
