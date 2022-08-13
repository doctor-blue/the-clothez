import { ProductColorRes } from "./ProductColorRes";
import { ProductSize } from "./ProductSize";

export class ProductColor {
    colorId: string;
    productId:string;
    name: string;
    description: string;
    hex: string;
    sizes: Array<ProductSize>;
    resources: Array<ProductColorRes>

    constructor(
        colorId: string,
        productId:string,
        name: string,
        description: string,
        hex: string,
        sizes: Array<ProductSize>,
        resources: Array<ProductColorRes>
    ) {
        this.colorId = colorId;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.hex = hex;
        this.sizes = sizes;
        this.resources = resources;
    }

    static fromObj(obj: any): ProductColor {
        const sizes = obj.sizes.map((value: any, index: number) => {
            return ProductSize.fromObj(value);
        });
        const resources = obj.resources.map((value: any, index: number) => {
            return ProductColorRes.fromObj(value);
        });

        return new ProductColor(
            obj.colorId,
            obj.productId,
            obj.name,
            obj.description,
            obj.hex,
            sizes,
            resources
        )
    }


}