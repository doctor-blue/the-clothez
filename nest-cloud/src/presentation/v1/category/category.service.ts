import { Injectable, InternalServerErrorException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { CategoryEntity } from 'src/data/entity/CategoryEntity';
import { SubCategoryEntity } from 'src/data/entity/SubCategoryEntity';
import MapperModule from 'src/di/MapperModule';
import { CREATE_CATEGORY_FAILURE, DELETE_CATEGORY_FAILURE, EXECUTE_QUERY_FAILURE, INVALID_CATEGORY_INFO } from 'src/domain/const/ErrorConst';
import { SUCCESS_STATUS } from 'src/domain/const/StatusConst';
import { Category, SubCategory } from 'src/domain/model/Category';
import { currentTime } from 'src/domain/utils/Time';
import { CategoryDto } from 'src/presentation/dto/CategoryDto';
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

    private subCategoryMapper = MapperModule.getInstance().provideSubCategoryMapper();

    async cretateCategory(category: Category): Promise<IResponse<string>> {

        if (
            !category.gender ||
            !category.name
        )
            throw new InternalServerErrorException(INVALID_CATEGORY_INFO.toJson());

        let result;

        try {
            result = await this.categoryRepo.createQueryBuilder().insert().into(CategoryEntity).values([
                {
                    gender: category.gender,
                    name: category.name,
                    description: category.description
                }
            ]).returning("id").execute()
        } catch (err) {
            throw new InternalServerErrorException(CREATE_CATEGORY_FAILURE.toJson());
        }

        if (!result) {
            throw new InternalServerErrorException(CREATE_CATEGORY_FAILURE.toJson());
        }

        return new IResponse(result.raw[0].id, SUCCESS_STATUS)
    }

    async updateCategory(category: Category): Promise<IResponse<boolean>> {
        if (
            !category.id ||
            !category.name ||
            !category.description
        )
            throw new InternalServerErrorException(INVALID_CATEGORY_INFO.toJson());

        let result;

        try {
            result = await this.categoryRepo.createQueryBuilder()
                .update(CategoryEntity).set({
                    updated_at: currentTime(),
                    gender: category.gender,
                    name: category.name,
                    description: category.description
                }).where("id=:id", { id: category.id }).execute();
            console.log(result);
        } catch (err) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }
        return new IResponse(true, SUCCESS_STATUS)
    }

    async deleteCategory(categoryId: string): Promise<IResponse<boolean>> {
        if (
            !categoryId
        )
            throw new InternalServerErrorException(INVALID_CATEGORY_INFO.toJson());
        const result = await this.categoryRepo.delete({
            id: categoryId
        });


        if (result.affected == 0) {
            throw new InternalServerErrorException(DELETE_CATEGORY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS)
    }

    async cretateSubCategory(subCategory: SubCategory): Promise<IResponse<string>> {

        if (
            !subCategory.categoryId ||
            !subCategory.name
        )
            throw new InternalServerErrorException(INVALID_CATEGORY_INFO.toJson());

        let result

        try {
            result = await this.categoryRepo.createQueryBuilder().insert().into(SubCategoryEntity).values([
                {
                    name: subCategory.name,
                    description: subCategory.description,
                    category_id: subCategory.categoryId
                }
            ]).returning("id").execute()
        } catch (error) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());

        }

        if (!result) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }

        return new IResponse(result.raw[0].id, SUCCESS_STATUS)
    }

    async updateSubCategory(subCategory: SubCategory): Promise<IResponse<boolean>> {
        if (
            !subCategory.id ||
            !subCategory.name ||
            !subCategory.description
        )
            throw new InternalServerErrorException(INVALID_CATEGORY_INFO.toJson());

        let result;
        try {
            result = await this.categoryRepo.createQueryBuilder()
                .update(SubCategoryEntity).set({
                    updated_at: currentTime(),
                    name: subCategory.name,
                    description: subCategory.description
                }).where("id=:id", { id: subCategory.id }).execute();

        } catch (error) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }
        if (result.affected == 0) {
            throw new InternalServerErrorException(EXECUTE_QUERY_FAILURE.toJson());
        }
        return new IResponse(true, SUCCESS_STATUS)
    }

    async deleteSubCategory(subCategoryId: string): Promise<IResponse<boolean>> {
        if (
            subCategoryId
        )
            throw new InternalServerErrorException(INVALID_CATEGORY_INFO.toJson());

        const result = await this.subCategoryRepo.delete({
            id: subCategoryId
        });


        if (result.affected == 0) {
            throw new InternalServerErrorException(DELETE_CATEGORY_FAILURE.toJson());
        }

        return new IResponse(true, SUCCESS_STATUS)
    }

    async getCategory(): Promise<IResponse<CategoryDto>> {

        const result = await this.categoryRepo.find();

        const categories = result.map((entity: CategoryEntity) => {
            return this.categoryMapper.toDomain(entity);
        });


        const subCategories = await (await this.subCategoryRepo.find()).map((subCategory: SubCategoryEntity) => this.subCategoryMapper.toDomain(subCategory));

        const response = new IResponse<CategoryDto>(
            new CategoryDto(categories, subCategories),
            SUCCESS_STATUS
        )
        return response;

    }




}
