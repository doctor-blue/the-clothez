export class ProductInfo {
    id: string;
    sizeId: string;
    colorId: string;
    productAmount: number;

    constructor(
        id: string,
        sizeId: string,
        colorId: string,
        productAmount: number
    ) {
        this.id = id;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.productAmount = productAmount;
    }
}