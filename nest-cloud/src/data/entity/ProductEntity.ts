export class ProductEntity {
    id: string;
    name: string;
    description: string;
    form: string;
    material: string;
    unit: string;
    product_code: string;
    quantity_per_unit: number;
    price: number;
    unit_price: string;
    sub_category_id: string;
    created_at: string;
    updated_at: string


    constructor(
        product_id: string,
        name: string,
        description: string,
        product_code: string,
        form: string,
        material: string,
        unit: string,
        quantity_per_unit: number,
        price: number,
        unit_price: string,
        sub_category_id: string,
        created_at: string,
        updated_at: string,
    ) {
        this.id = product_id;
        this.name = name;
        this.description = description;
        this.product_code = product_code;
        this.form = form;
        this.material = material;
        this.unit = unit;
        this.quantity_per_unit = quantity_per_unit;
        this.price = price;
        this.unit_price = unit_price;
        this.sub_category_id = sub_category_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


}