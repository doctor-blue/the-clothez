
export class CategoryEntity {
    id: string;
    name: string;
    description: string;
    gender: number;
    createdAt?: string;
    updatedAt?: string;


    constructor(id: string,
        name: string,
        description: string,
        gender: number,
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gender = gender;
    }
}