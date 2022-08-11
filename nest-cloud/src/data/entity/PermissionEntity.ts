import User from 'src/domain/model/User';
import { Entity, Column, PrimaryGeneratedColumn, OneToMany } from 'typeorm'
import { UserEntity } from './UserEntity';

@Entity("permission")
export class PermissionEntity {
    @PrimaryGeneratedColumn()
    permission_id: number;

    @Column()
    permission_type: number;

    @Column({ nullable: true })
    description: string;

    @Column({ nullable: true })
    created_at: Date;

    @Column({ nullable: true })
    updated_at: Date;

    @OneToMany(() => UserEntity,
        (user: UserEntity) => user.permission)
    public users: UserEntity[];
}