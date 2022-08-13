import { SubCategory } from "src/domain/model/Category";
import { currentTime } from "src/domain/utils/Time";
import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { IEntity } from "./IEntity";
import { SubCategoryEntity } from "./SubCategoryEntity";

@Entity("category")
export class CategoryEntity implements IEntity {
    @PrimaryGeneratedColumn("uuid")
    id: string;
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
        nullable: true
    })
    gender: number;

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

    @OneToMany(() => SubCategoryEntity,
        (subCate: SubCategoryEntity) => subCate.category)
    public subCategories: SubCategory[];

    constructor(
        id: string,
        name: string,
        description: string,
        gender: number,
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gender = gender;
    }
}