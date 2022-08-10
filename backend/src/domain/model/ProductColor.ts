import { ProductColorRes } from "./ProductColorRes";
import { ProductSize } from "./ProductSize";

export class ProductColor {
    color_id: string;
    name: string;
    description: string;
    hex: string;
    sizes: Array<ProductSize>;
    resources: Array<ProductColorRes>

    constructor(
        color_id: string,
        name: string,
        description: string,
        hex: string,
        sizes: Array<ProductSize>,
        resources: Array<ProductColorRes>
    ) {
        this.color_id = color_id;
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
            obj.color_id,
            obj.name,
            obj.description,
            obj.hex,
            sizes,
            resources
        )
    }


}