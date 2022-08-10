import { ProductColor } from "./ProductColor";

export class Product {
    product_id: string;
    name: string;
    description: string;
    product_code: string;
    form: string;
    material: string;
    unit: string;
    price: number;
    unit_price: string;
    sub_category_id: string;
    colors: Array<ProductColor>

    constructor(
        product_id: string,
        name: string,
        description: string,
        product_code: string,
        form: string,
        material: string,
        unit: string,
        price: number,
        unit_price: string,
        sub_category_id: string,
        colors: Array<ProductColor>
    ) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.product_code = product_code;
        this.form = form;
        this.material = material;
        this.unit = unit;
        this.price = price;
        this.unit_price = unit_price;
        this.sub_category_id = sub_category_id;
        this.colors = colors;
    }

}

