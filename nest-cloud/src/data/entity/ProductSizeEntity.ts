import { ProductColor } from "src/domain/model/ProductColor";
import { currentTime } from "src/domain/utils/Time";
import { Column, Entity, ManyToOne, PrimaryGeneratedColumn } from "typeorm";
import { ProductColorEntity } from "./ProductColorEntity";

@Entity("product_size_info")
export class ProductSizeEntity {
    @PrimaryGeneratedColumn("uuid")
    id: string;
    @Column({
        nullable: true,
    })
    color_id: string;
    @Column({
        nullable: true,
        length: 50
    })
    size: string;
    @Column({
        nullable: true,
        default: 0
    })
    product_amount: number;
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
        () => ProductColorEntity,
        (color) => color.sizeList
    )
    color: ProductColorEntity;

    constructor(
        size_id: string,
        color_id: string,
        size: string,
        product_amount: number,
        created_at: Date,
        updated_at: Date,
    ) {
        this.id = size_id;
        this.color_id = color_id;
        this.size = size;
        this.product_amount = product_amount;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}