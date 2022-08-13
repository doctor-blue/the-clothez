import { currentTime } from "src/domain/utils/Time";
import { Column, Entity, ManyToOne, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { IEntity } from "./IEntity";
import { ProductColorEntity } from "./ProductColorEntity";
import { SubCategoryEntity } from "./SubCategoryEntity";

@Entity("product")
export class ProductEntity implements IEntity {
    @PrimaryGeneratedColumn('uuid')
    id: string;
    @Column({
        nullable: true
    })
    name: string;
    @Column({
        nullable: true
    })
    description: string;
    @Column({
        nullable: true
    })
    form: string;
    @Column({
        nullable: true
    })
    material: string;
    @Column({
        nullable: true,
        length: 254
    })
    unit: string;
    @Column({
        nullable: true,
        length: 254
    })
    product_code: string;
    @Column({
        nullable: true
    })
    quantity_per_unit: number;
    @Column({
        nullable: true,
        default: 0
    })
    price: number;
    @Column({
        nullable: true,
        length: 100
    })
    unit_price: string;
    @Column({
        nullable: true
    })
    sub_category_id: string;

    @Column({
        nullable: true,
        default: currentTime()
    })
    created_at: Date;
    @Column({
        nullable: true,
        default: currentTime()
    })
    updated_at: Date;

    @ManyToOne(() => SubCategoryEntity, (subCate) => subCate.products)
    subCategory: SubCategoryEntity

    @OneToMany(
        () => ProductColorEntity,
        (color) => color.product
    )
    colors: ProductColorEntity[]


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
        created_at: Date,
        updated_at: Date,
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