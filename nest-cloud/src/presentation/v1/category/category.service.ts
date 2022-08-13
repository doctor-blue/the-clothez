import { Injectable, InternalServerErrorException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { CategoryEntity } from 'src/data/entity/CategoryEntity';
import { SubCategoryEntity } from 'src/data/entity/SubCategoryEntity';
import MapperModule from 'src/di/MapperModule';
import { CREATE_CATEGORY_FAILURE, DELETE_CATEGORY_FAILURE } from 'src/domain/const/ErrorConst';
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

    async cretateSubCategory(subCategory: SubCategory): Promise<IResponse<string>> {
        const result = await this.categoryRepo.createQueryBuilder().insert().into(SubCategoryEntity).values([
            {
                name: subCategory.name,
                description: subCategory.description,
                category_id: subCategory.category_id
            }
        ]).returning("id").execute()

        if (!result) {
            throw new InternalServerErrorException(CREATE_CATEGORY_FAILURE.toJson());
        }

        return new IResponse(result.raw[0].id, SUCCESS_STATUS)
    }

    async updateSubCategory(subCategory: SubCategory): Promise<IResponse<boolean>> {

        const result = await this.categoryRepo.createQueryBuilder()
            .update(SubCategoryEntity).set({
                updated_at: currentTime(),
                name: subCategory.name,
                description: subCategory.description
            }).where("id=:id", { id: subCategory.id }).execute();

        if (result.affected == 0) {
            throw new InternalServerErrorException(CREATE_CATEGORY_FAILURE.toJson());
        }
        return new IResponse(true, SUCCESS_STATUS)
    }

    async deleteSubCategory(subCategoryId: string): Promise<IResponse<boolean>> {

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

        
        const subCategories = await (await this.subCategoryRepo.find()).map((subCategory:SubCategoryEntity)=> this.subCategoryMapper.toDomain(subCategory));

        const response = new IResponse<CategoryDto>(
            new CategoryDto(categories, subCategories),
            SUCCESS_STATUS
        )
        return response;

    }




}
