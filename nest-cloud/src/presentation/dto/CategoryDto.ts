import { Category, SubCategory } from "src/domain/model/Category";

export class CategoryDto {
    categories: Array<Category>;
    subCategories: Array<SubCategory>;

    constructor(categories: Array<Category>, subCategories: Array<SubCategory>) {
        this.categories = categories;
        this.subCategories = subCategories;
    }

}