export class ProductSize {
    sizeId: string;
    colorId: string;
    size: string;
    productAmount: number;

    constructor(sizeId: string, colorId: string, size: string, productAmount: number) {
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.size = size;
        this.productAmount = productAmount;
    }

    static fromObj(obj: any): ProductSize {
        return new ProductSize(
            obj.sizeId,
            obj.colorId,
            obj.size,
            obj.productAmount
        )
    }
}