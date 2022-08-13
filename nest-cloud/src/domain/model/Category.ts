export class Category {
    id: string;
    name: string;
    gender: number;
    description: string;
    constructor(id: string, name: string, gender: number, description: string) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.description = description;
    }
    static fromObj(obj: any): Category {
        return new Category(
            obj.id,
            obj.name,
            obj.gender,
            obj.description,
        );
    }
}

export class SubCategory {
    id: string;
    categoryId: string;
    name: string;
    description: string;
    constructor(id: string, categoryId: string, name: string, description: string) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    static fromObj(obj: any): SubCategory {
        return new SubCategory(
            obj.id,
            obj.categoryId,
            obj.name,
            obj.description,
        );
    }
};

export class CategoryInfo {
    categories: Array<Category>;
    subCategories: Array<SubCategory>;

    constructor(
        categories: Array<Category>,
        subCategories: Array<SubCategory>
    ) {
        this.categories = categories;
        this.subCategories = subCategories;
    }
}