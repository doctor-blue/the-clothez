import { ProductColor } from "src/domain/model/ProductColor";
import { currentTime } from "src/domain/utils/Time";
import { Column, Entity, JoinColumn, ManyToOne, PrimaryGeneratedColumn } from "typeorm";
import { ProductColorEntity } from "./ProductColorEntity";

@Entity("product_color_res")
export class ProductColorResEntity {
    @PrimaryGeneratedColumn("uuid")
    id: string;
    @Column({
        nullable: true
    })
    color_id: string;
    @Column({
        nullable: true
    })
    url: string;
    @Column({
        nullable: true
    })
    description: string;
    @Column({
        nullable: true,
        default: 0
    })
    res_type: number;
    @Column({
        nullable: true,
        default: 0
    })
    mine_type: number;
    @Column({
        nullable: true,
        default: currentTime()
    })
    created_at: Date;
    @Column({
        nullable: true,
        default: currentTime()
    })
    updated_at: Date

    @ManyToOne(
        () => ProductColorEntity,
        (color) => color.resources,
        { onDelete: "CASCADE" }
    )
    @JoinColumn({ name: "color_id" })
    color: ProductColorEntity;

    constructor(
        res_id: string,
        color_id: string,
        url: string,
        description: string,
        res_type: number,
        mine_type: number,
        created_at: Date,
        updated_at: Date,
    ) {
        this.id = res_id;
        this.color_id = color_id;
        this.url = url;
        this.description = description;
        this.res_type = res_type;
        this.mine_type = mine_type;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}