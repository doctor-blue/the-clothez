import {
    Entity,
    Column,
    PrimaryGeneratedColumn,
    ManyToOne,
    JoinColumn,
} from 'typeorm'
import { PermissionEntity } from './PermissionEntity';
@Entity("users")
export class UserEntity implements Entity {
    @PrimaryGeneratedColumn("uuid")
    user_id: string;
    @Column()
    first_name: string;
    @Column()
    last_name: string;
    @Column()
    user_name: string;
    @Column({
        length: 254
    })
    email: string;
    @Column({
        length: 20,
        nullable: true
    })
    phone_number?: string;
    @Column({
        length: 254
    })
    password: string;
    @Column({ nullable: true })
    created_at: Date;
    @Column({ nullable: true })
    updated_at: Date;
    @Column()
    avatar: string;
    @Column({ nullable: true })
    gender: number;
    @Column({ default: true })
    is_active: boolean;
    @Column({ nullable: true })
    dob?: Date;
    @Column({ nullable: true })
    permission_id: string;

    @ManyToOne(() => PermissionEntity, (permission: PermissionEntity) => permission.permission_id)
    permission: PermissionEntity


    // constructor(user_id: string,
    //     first_name: string,
    //     last_name: string,
    //     user_name: string,
    //     email: string,
    //     phone_number: string,
    //     password: string,
    //     created_at: string,
    //     updated_at: string,
    //     avartar: string,
    //     gender: number,
    //     is_active: boolean,
    //     dob: string,
    //     permissionId: string) {

    //     this.user_id = user_id;
    //     this.first_name = first_name;
    //     this.last_name = last_name;
    //     this.user_name = user_name;
    //     this.email = email;
    //     this.phone_number = phone_number;
    //     this.password = password;
    //     this.created_at = created_at;
    //     this.updated_at = updated_at;
    //     this.avatar = avartar;
    //     this.is_active = is_active;
    //     this.dob = dob;
    //     this.permission_id = permissionId;
    //     this.gender = gender;
    // }
}