import { NestFactory } from '@nestjs/core';
import { delay } from 'rxjs';
import { Category, SubCategory } from 'src/domain/model/Category';
import { AppModule } from '../app.module';
import { CategoryController } from '../v1/category/category.controller';
import simualationData from './simulationdata_vi.json'

export async function bootstrapSimulate() {
    const app = await NestFactory.create(AppModule);
    await app.listen(4000);
    const categoryController = app.get(CategoryController)
    for (let index = 0; index < simualationData.length; index++) {
        const element = simualationData[index];
        const category = Category.fromObj(element)
        const result = await categoryController.createCategory(category);
        if (result.data) {
            for (let i = 0; i < element.subCategories.length; i++) {
                const subCate = element.subCategories[i];
                const subCategory = SubCategory.fromObj(subCate);
                subCategory.categoryId = result.data;
                const subCateResult = await categoryController.createSubCategory(subCategory);
                if (subCateResult.data)
                    console.log("Add sub category ", subCate.name, " success!");
                else
                    console.log("Add sub category ", subCate.name, " failure!");

            }
            console.log("\nAdd category ", element.name, " success!");
        } else {
            console.log("\nAdd category ", element.name, " failure!");
        }
    }
    console.log("Data simulation is complete, please cancel!");
    app.close();
}
