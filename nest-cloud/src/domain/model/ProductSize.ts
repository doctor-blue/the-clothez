export class ProductSize {
    sizeId: string;
    productId: string;
    size: string;

    constructor(sizeId: string, productId: string, size: string) {
        this.sizeId = sizeId;
        this.productId = productId;
        this.size = size;
    }

    static fromObj(obj: any): ProductSize {
        return new ProductSize(
            obj.sizeId,
            obj.productId,
            obj.size,
        )
    }
}