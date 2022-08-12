import { Category } from "src/domain/model/Category";
import { currentTime } from "src/domain/utils/Time";
import { Column, Entity, ManyToOne, PrimaryGeneratedColumn } from "typeorm";
import { CategoryEntity } from "./CategoryEntity";
import { IEntity } from "./IEntity";

@Entity("sub_category")
export class SubCategoryEntity implements IEntity {

    @PrimaryGeneratedColumn("uuid")
    id: string;

    @Column()
    category_id: string;

    @Column({
        length: 254,
        nullable: true
    })
    name: string;

    @Column()
    gender: number;

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
            category.subCategories)
    category: CategoryEntity

    constructor(
        id: string,
        category_id: string,
        name: string,
        description: string,
        gender: number,
    ) {
        this.id = id;
        this.category_id = category_id;
        this.description = description;
        this.name = name;
        this.gender = gender;
    }

}