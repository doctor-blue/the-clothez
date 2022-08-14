import { ProductColorRes } from "./ProductColorRes";

export class ProductColor {
    colorId: string;
    productId:string;
    name: string;
    description: string;
    hex: string;
    resources: Array<ProductColorRes>

    constructor(
        colorId: string,
        productId:string,
        name: string,
        description: string,
        hex: string,
        resources: Array<ProductColorRes>
    ) {
        this.colorId = colorId;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.hex = hex;
        this.resources = resources;
    }

    static fromObj(obj: any): ProductColor {
        const resources = obj.resources.map((value: any, index: number) => {
            return ProductColorRes.fromObj(value);
        });

        return new ProductColor(
            obj.colorId,
            obj.productId,
            obj.name,
            obj.description,
            obj.hex,
            resources
        )
    }


}