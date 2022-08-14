import { currentTime } from "src/domain/utils/Time";
import { Column, Entity, JoinColumn, ManyToOne, PrimaryGeneratedColumn } from "typeorm";
import { IEntity } from "./IEntity";
import { ProductInfoEntity } from "./ProductInfoEntity";
import { UserEntity } from "./UserEntity";


@Entity("cart_items")
export class CartItemEntity implements IEntity {
    @PrimaryGeneratedColumn("uuid")
    id: string;

    @Column({
        nullable: true
    })
    product_info_id: string;

    @Column(
        {
            nullable: true
        }
    )
    user_id: string;

    @Column({
        nullable: true
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
        () => ProductInfoEntity,
        (info) => info.cartItems,
        { onDelete: "CASCADE" }
    )
    @JoinColumn({ name: "product_info_id" })
    productInfo: ProductInfoEntity;

    @ManyToOne(
        () => UserEntity,
        (user) => user.cartItems,
        { onDelete: "CASCADE" }
    )
    @JoinColumn({ name: "user_id" })
    user: UserEntity;


    constructor(
        id:string,
        product_info_id:string,
        user_id:string,
        product_amount:number,
    ){
        this.id = id;
        this.product_info_id = product_info_id;
        this.user_id = user_id;
        this.product_amount = product_amount;
    }
}