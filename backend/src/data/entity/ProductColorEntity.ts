export class ProductColorEntity {
    color_id: string;
    name: string;
    description: string;
    hex: string;
    created_at: string;
    updated_at: string

    constructor(
        color_id: string,
        name: string,
        description: string,
        hex: string,
        created_at: string,
        updated_at: string,
    ) {
        this.color_id = color_id;
        this.name = name;
        this.description = description;
        this.hex = hex;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    
}