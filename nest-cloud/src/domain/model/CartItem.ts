export class CartItem {
    id: string;
    productInfoId: string;
    userId: string;
    productAmount: number;

    constructor(
        id: string,
        productInfoId: string,
        userId: string,
        productAmount: number
    ) {
        this.id = id;
        this.productInfoId = productInfoId;
        this.userId = userId;
        this.productAmount = productAmount;
    }
}