import { Category, CategoryInfo, SubCategory } from "../../domain/model/Category";
import { Status } from "../../domain/model/Status";
import { CategoryRepository } from "../../domain/repository/CategoryRepository";
import StateCallback from "../../domain/utils/StateCallback";
import { postgresPool } from "../source/db";


export class CategoryRepositoryImpl implements CategoryRepository {

    createCategory(category: Category, callback: StateCallback<string, Status>) {
        this.startCreateCategory(category, callback)
    }

    updateCategory(category: Category, callback: StateCallback<boolean, Status>) {
        this.startUpdateCategoryQuery(category, callback);
    }

    deleteCategory(categoryId: string, callback: StateCallback<boolean, Status>) {
        this.startDeleteCategoryQuery(categoryId, callback);
    }

    getAllCategories(callback: StateCallback<CategoryInfo, Status>) {
        this.startGetAllCategories(callback)

    }

    createSubCategory(subCategory: SubCategory, callback: StateCallback<string, Status>) {
        this.startCreateSubCategory(subCategory, callback)
    }

    updateSubCategory(subCategory: SubCategory, callback: StateCallback<boolean, Status>) {
        this.startUpdateSubCategory(subCategory, callback)
    }

    deleteSubCategory(subCategoryId: string, callback: StateCallback<boolean, Status>) {
        this.startDeleteSubCategoryQuery(subCategoryId, callback)
    }

    private getCategoriesQuery = "SELECT * FROM category";
    private getSubCategoriesQuery = "SELECT * FROM sub_category"
    private async startGetAllCategories(callback: StateCallback<CategoryInfo, Status>) {
        try {
            const categoryResult = await postgresPool.query(this.getCategoriesQuery);
            // if (categoryResult.rows.length === 0) {
            //     callback.onFailure(403, new Status(403, "Get categories failure."))
            //     return;
            // }
            console.log("after get category");


            const subCategoriesResult = await postgresPool.query(this.getSubCategoriesQuery);

            // if (subCategoriesResult.rows.length === 0) {
            //     callback.onFailure(403, new Status(403, "Get categories failure."))
            //     return;
            // }
            const categories: Array<Category> = []
            categoryResult.rows.forEach((data) => {
                categories.push(Category.fromObj(data))
            })

            const subCategories: Array<SubCategory> = [];

            subCategoriesResult.rows.forEach(element => {
                subCategories.push(SubCategory.fromObj(element));
            });


            callback.onSuccess(new CategoryInfo(
                categories, subCategories
            ));

        } catch (err) {
            callback.onFailure(403, new Status(403, "Get categories failure " + err))
        }
    }


    private createCategoryQuery = "INSERT INTO CATEGORY(gender,name,description) VALUES($1,$2,$3) RETURNING *"
    private async startCreateCategory(category: Category, callback: StateCallback<string, Status>) {
        try {
            const result = await postgresPool.query(this.createCategoryQuery, [
                category.gender, category.name, category.description
            ]);
            if (result.rows.length === 0) {
                callback.onFailure(403, new Status(403, "Cretate category failure."))
                return;
            }
            callback.onSuccess(result.rows[0].id);

        } catch (err) {
            callback.onFailure(403, new Status(403, "Cretate category failure " + err))
        }
    }

    private updateCategoryQuery = "UPDATE CATEGORY SET name=$2, description=$3, updated_at=CURRENT_TIMESTAMP WHERE category_id = $1 RETURNING *"
    private async startUpdateCategoryQuery(category: Category, callback: StateCallback<boolean, Status>) {
        try {
            const result = await postgresPool.query(this.updateCategoryQuery, [
                category.id, category.name, category.description
            ]);
            if (result.rows.length === 0) {
                callback.onFailure(403, new Status(403, "Update category failure."))
                return;
            }

            callback.onSuccess(result.rows.length > 0);

        } catch (err) {
            callback.onFailure(403, new Status(403, "Update category failure " + err))
        }
    }

    private deleteCategoryQuery = "DELETE FROM CATEGORY WHERE id = $1 RETURNING *"
    private async startDeleteCategoryQuery(categoryId: string, callback: StateCallback<boolean, Status>) {
        try {
            const result = await postgresPool.query(this.deleteCategoryQuery, [categoryId]);
            if (result.rows.length === 0) {
                callback.onFailure(204, new Status(204, "Update category failure."))
                return;
            }

            callback.onSuccess(result.rows[0] !== 0);

        } catch (err) {
            callback.onFailure(202, new Status(202, "Delete category failure " + err))
        }
    }


    private createSubCategoryQuery = "INSERT INTO SUB_CATEGORY(category_id,name,description) VALUES($1,$2,$3) RETURNING *"
    private async startCreateSubCategory(subCategory: SubCategory, callback: StateCallback<string, Status>) {
        try {
            console.log(subCategory);

            // insert category first
            const result = await postgresPool.query(this.createSubCategoryQuery, [
                subCategory.category_id, subCategory.name, subCategory.description
            ]);

            if (result.rows.length === 0) {
                callback.onFailure(403, new Status(403, "Cretate sub category failure."))
                return;
            }


            callback.onSuccess(result.rows[0].id);

        } catch (err) {
            callback.onFailure(403, new Status(403, "Cretate sub category failure " + err))
        }
    }

    private updateSubCategoryQuery = "UPDATE SUB_CATEGORY SET name = $2, description = $3 WHERE sub_category_id = $1 RETURNING *"
    private async startUpdateSubCategory(subCategory: SubCategory, callback: StateCallback<boolean, Status>) {
        try {
            const result = await postgresPool.query(this.updateSubCategoryQuery, [
                subCategory.id, subCategory.name, subCategory.description
            ]);
            if (result.rows.length === 0) {
                callback.onFailure(403, new Status(403, "Update category failure."))
                return;
            }

            callback.onSuccess(result.rows[0].id);

        } catch (err) {
            callback.onFailure(403, new Status(403, "Update category failure " + err))
        }
    }

    private deleteSubCategoryQuery = "DELETE FROM SUB_CATEGORY WHERE id = $1 RETURNING *"
    private async startDeleteSubCategoryQuery(subCategoryId: string, callback: StateCallback<boolean, Status>) {
        try {
            const result = await postgresPool.query(this.deleteSubCategoryQuery, [subCategoryId]);
            if (result.rows.length === 0) {
                callback.onFailure(204, new Status(204, "Delete subcategory failure."))
                return;
            }

            callback.onSuccess(result.rows[0] !== 0);

        } catch (err) {
            callback.onFailure(202, new Status(202, "Delete category failure "))
        }
    }






}