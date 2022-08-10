export class ProductSizeEntity {
    size_id: string;
    color_id: string;
    size: string;
    product_amount: number;
    created_at: string;
    updated_at: string

    constructor(
        size_id: string,
        color_id: string,
        size: string,
        product_amount: number,
        created_at: string,
        updated_at: string,
    ) {
        this.size_id = size_id;
        this.color_id = color_id;
        this.size = size;
        this.product_amount = product_amount;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}