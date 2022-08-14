import { Category } from "src/domain/model/Category";
import { currentTime } from "src/domain/utils/Time";
import { Column, Entity, JoinColumn, ManyToOne, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { CategoryEntity } from "./CategoryEntity";
import { IEntity } from "./IEntity";
import { ProductEntity } from "./ProductEntity";

@Entity("sub_category")
export class SubCategoryEntity implements IEntity {

    @PrimaryGeneratedColumn("uuid")
    id: string;

    @Column({
        nullable: true
    })
    category_id: string;

    @Column({
        length: 254,
        nullable: true
    })
    name: string;

    @Column({
        length: 254,
        nullable: true
    })
    description: string;

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

    @ManyToOne(() => CategoryEntity,
        (category: CategoryEntity) =>
            category.subCategories, { onDelete: 'CASCADE' })
    @JoinColumn({ name: "category_id" })
    category: CategoryEntity

    @OneToMany(() => ProductEntity, (product: ProductEntity) => product.subCategory)
    products: ProductEntity[]

    constructor(
        id: string,
        category_id: string,
        name: string,
        description: string,
    ) {
        this.id = id;
        this.category_id = category_id;
        this.description = description;
        this.name = name;
    }

}