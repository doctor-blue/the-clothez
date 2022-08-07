import { Category, SubCategory } from "../../domain/model/Category";
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

    getAllCategories(callback: StateCallback<string, Status>) {

    }

    createSubCategory(subCategory: SubCategory, callback: StateCallback<string, Status>) {
        this.startCreateSubCategory(subCategory, callback)
    }

    updateSubCategory(subCategory: SubCategory, callback: StateCallback<boolean, Status>) {
        this.startUpdateSubCategory(subCategory, callback)
    }

    deleteSubCategory(subCategoryId: string, callback: StateCallback<boolean, Status>) {
    }


    private createCategoryQuery = "INSERT INTO CATEGORY(gender) VALUES($1) RETURNING *"
    private createCategoryDescQuery = "INSERT INTO CATEGORY_DESC(category_id,name,description,lang) VALUES($1,$2,$3,$4) RETURNING *"
    private async startCreateCategory(category: Category, callback: StateCallback<string, Status>) {
        try {
            const result = await postgresPool.query(this.createCategoryQuery, [
                category.gender
            ]);
            if (result.rows.length === 0) {
                callback.onFailure(403, new Status(403, "Cretate category failure."))
                return;
            }
            const descResult = await postgresPool.query(this.createCategoryDescQuery, [
                result.rows[0].id, category.name, category.description, category.lang
            ]);
            if (descResult.rows.length === 0) {
                callback.onFailure(403, new Status(403, "Cretate category desc failure."))
                return;
            }
            callback.onSuccess(result.rows[0].id);

        } catch (err) {
            callback.onFailure(403, new Status(403, "Cretate category failure " + err))
        }
    }

    private updateCategoryQuery = "UPDATE CATEGORY_DESC SET name=$3, description=$4, updated_at=CURRENT_TIMESTAMP WHERE category_id = $1 AND lang=$2 RETURNING *"
    private async startUpdateCategoryQuery(category: Category, callback: StateCallback<boolean, Status>) {
        try {
            const result = await postgresPool.query(this.updateCategoryQuery, [
                category.id, category.lang, category.name, category.description
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
            callback.onFailure(202, new Status(202, "Delete category failure "+err))
        }
    }


    private createSubCategoryQuery = "INSERT INTO SUB_CATEGORY(category_id) VALUES($1) RETURNING *"
    private createSubCategoryDescQuery = "INSERT INTO SUB_CATEGORY_DESC(sub_category_id,name,description,lang) VALUES($1,$2,$3,$4) RETURNING *"

    private async startCreateSubCategory(subCategory: SubCategory, callback: StateCallback<string, Status>) {
        try {
            const result = await postgresPool.query(this.createSubCategoryQuery, [
                subCategory.category_id, subCategory.name, subCategory.description
            ]);

            if (result.rows.length === 0) {
                callback.onFailure(403, new Status(403, "Cretate category failure."))
                return;
            }

            const descResult = await postgresPool.query(this.createSubCategoryDescQuery, [
                result.rows[0].id, subCategory.name, subCategory.description, subCategory.lang
            ]);
            if (descResult.rows.length === 0) {
                callback.onFailure(403, new Status(403, "Cretate category desc failure."))
                return;
            }

            callback.onSuccess(result.rows[0].id);

        } catch (err) {
            callback.onFailure(403, new Status(403, "Cretate category failure " + err))
        }
    }

    private updateSubCategoryQuery = "UPDATE SUB_CATEGORY_DES SET name = $3, description = $4 WHERE sub_category_id = $1 AND lang =$2 RETURNING *"
    private async startUpdateSubCategory(subCategory: SubCategory, callback: StateCallback<boolean, Status>) {
        try {
            const result = await postgresPool.query(this.updateSubCategoryQuery, [
                subCategory.id, subCategory.lang, subCategory.name, subCategory.description
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