export class SubCategoryEntity {
    id: string;
    category_id: string;
    name: string;
    gender: number;
    description: string;

    constructor(
        id: string,
        category_id: string,
        name: string,
        description: string,
        gender: number,
    ) {
        this.id = id;
        this.category_id = category_id;
        this.description = description;
        this.name = name;
        this.gender = gender;
    }

}