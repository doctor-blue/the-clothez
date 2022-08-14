import { currentTime } from "src/domain/utils/Time";
import { Column, Entity, JoinColumn, ManyToOne, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { IEntity } from "./IEntity";
import { ProductEntity } from "./ProductEntity";
import { ProductInfoEntity } from "./ProductInfoEntity";

@Entity("product_size")
export class ProductSizeEntity implements IEntity {
    @PrimaryGeneratedColumn("uuid")
    id: string;
    @Column({
        nullable: true,
    })
    product_id: string;
    @Column({
        nullable: true,
        length: 50
    })
    size: string;

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
        (product) => product.sizeList,
        { onDelete: "CASCADE" }
    )
    @JoinColumn({ name: "product_id" })
    product: ProductEntity;

    @OneToMany(
        () => ProductInfoEntity,
        (info) => info.size_id,
    )
    productInfos: ProductInfoEntity[];

    constructor(
        size_id: string,
        product_id: string,
        size: string,
        created_at: Date,
        updated_at: Date,
    ) {
        this.id = size_id;
        this.product_id = product_id;
        this.size = size;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}