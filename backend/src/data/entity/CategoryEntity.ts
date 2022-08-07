
export class CategoryEntity {
    id: string;
    name: string;
    description: string;
    gender: number;
    lang: string;
    createdAt?: string;
    updatedAt?: string;


    constructor(id: string,
        name: string,
        description: string,
        gender: number,
        lang: string,
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.lang = lang
    }
}