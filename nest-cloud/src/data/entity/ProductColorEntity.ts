import { currentTime } from "src/domain/utils/Time";
import { Column, Entity, JoinColumn, ManyToOne, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { IEntity } from "./IEntity";
import { ProductColorResEntity } from "./ProductColorResEntity";
import { ProductEntity } from "./ProductEntity";
import { ProductInfoEntity } from "./ProductInfoEntity";
import { ProductSizeEntity } from "./ProductSizeEntity";

@Entity("product_color")
export class ProductColorEntity implements IEntity {
    @PrimaryGeneratedColumn("uuid")
    id: string;
    @Column({
        nullable: true,
        length: 254
    })
    name: string;

    @Column({
        nullable: true
    })
    product_id: string;

    @Column({
        nullable: true,
    })
    description: string;

    @Column({
        nullable: true,
        length: 20
    })
    color_hex: string;

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

    @ManyToOne(
        () => ProductEntity,
        (product) => product.colors,
        { onDelete: "CASCADE" }
    )
    @JoinColumn({ name: "product_id" })
    product: ProductEntity;


    @OneToMany(
        () => ProductColorResEntity,
        (res) => res.color
    )
    resources: ProductColorResEntity[]

    @OneToMany(
        () => ProductInfoEntity,
        (info) => info.colors,
    )
    productInfo: ProductInfoEntity;

    constructor(
        color_id: string,
        product_id: string,
        name: string,
        description: string,
        hex: string,
        created_at: Date,
        updated_at: Date,
    ) {
        this.id = color_id;
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.color_hex = hex;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }



}