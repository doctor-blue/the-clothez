import { ProductColor } from "./ProductColor";

export class Product {
    productId: string;
    name: string;
    description: string;
    productCode: string;
    form: string;
    material: string;
    unit: string;
    quantityPerUnit: number;
    price: number;
    unitPrice: string;
    subCategoryId: string;
    colors: Array<ProductColor>

    constructor(
        productId: string,
        name: string,
        description: string,
        productCode: string,
        form: string,
        material: string,
        unit: string,
        quantityPerUnit: number,
        price: number,
        unitPrice: string,
        subCategoryId: string,
        colors: Array<ProductColor>
    ) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.productCode = productCode;
        this.form = form;
        this.material = material;
        this.unit = unit;
        this.quantityPerUnit = quantityPerUnit;
        this.price = price;
        this.unitPrice = unitPrice;
        this.subCategoryId = subCategoryId;
        this.colors = colors;
    }

    static fromObj(obj: any): Product {
        const colors = obj.colors.map((value: any, index: number) => {
            return ProductColor.fromObj(value);
        });
        return new Product(
            obj.productId,
            obj.name,
            obj.description,
            obj.productCode,
            obj.form,
            obj.material,
            obj.unit,
            obj.quantityPerUnit,
            obj.price,
            obj.unitPrice,
            obj.subCategoryId,
            colors
        )
    }

}

