export class ProductSize {
    size_id: string;
    color_id: string;
    size: string;
    product_amount: number;

    constructor(size_id: string, color_id: string, size: string, product_amount: number) {
        this.size_id = size_id;
        this.color_id = color_id;
        this.size = size;
        this.product_amount = product_amount;
    }

    static fromObj(obj: any): ProductSize {
        return new ProductSize(
            obj.size_id,
            obj.color_id,
            obj.size,
            obj.product_amount
        )
    }
}