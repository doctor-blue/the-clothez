import { Column, Entity, JoinColumn, ManyToOne, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { CartItemEntity } from "./CartItemEntity";
import { ProductColorEntity } from "./ProductColorEntity";
import { ProductSizeEntity } from "./ProductSizeEntity";

@Entity("product_info")
export class ProductInfoEntity {

    @PrimaryGeneratedColumn("uuid")
    id: string;

    @Column({
        nullable: true
    })
    size_id: string;

    @Column({
        nullable: true
    })
    color_id: string;

    @Column({
        nullable: true
    })
    product_amount: number;

    @ManyToOne(
        () => ProductSizeEntity,
        (size) => size.productInfos,
        { onDelete: "CASCADE" }
    )
    @JoinColumn({ name: "size_id" })
    size: ProductSizeEntity;

    @ManyToOne(
        () => ProductColorEntity,
        (color) => color.created_at,
        { onDelete: "CASCADE" }
    )
    @JoinColumn({ name: "color_id" })
    colors: ProductColorEntity[];

    
    @OneToMany(
        () => CartItemEntity,
        (item) => item.productInfo
    )
    cartItems: CartItemEntity[];


    constructor(
        id: string,
        size_id: string,
        color_id: string,
        product_amount: number
    ) {
        this.id = id;
        this.size_id = size_id;
        this.color_id = color_id;
        this.product_amount = product_amount;
    }

}