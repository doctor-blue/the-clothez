export class Category {
    id: string;
    name: string;
    gender: number;
    description: string;
    lang?: string;
    constructor(id: string, name: string, gender: number, description: string, lang: string) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.lang = lang;
    }
    static fromObj(obj: any): Category {
        return new Category(
            obj.id,
            obj.name,
            obj.gender,
            obj.description,
            obj.lang
        );
    }
}

export class SubCategory {
    id: string;
    category_id: string;
    name: string;
    description: string;
    lang?: string;
    constructor(id: string, category_id: string, name: string, description: string, lang: string) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.lang = lang;
    }

    static fromObj(obj: any): SubCategory {
        return new SubCategory(
            obj.id,
            obj.category_id,
            obj.name,
            obj.description,
            obj.lang
        );
    }
}