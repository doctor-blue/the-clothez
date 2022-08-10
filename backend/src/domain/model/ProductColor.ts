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

 
}