import User from 'src/domain/model/User';
import { currentTime } from 'src/domain/utils/Time';
import { Entity, Column, PrimaryGeneratedColumn, OneToMany } from 'typeorm'
import { IEntity } from './IEntity';
import { UserEntity } from './UserEntity';

@Entity("permission")
export class PermissionEntity implements IEntity {
    @PrimaryGeneratedColumn()
    permission_id: number;

    @Column()
    permission_type: number;

    @Column({ nullable: true })
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

    @OneToMany(() => UserEntity,
        (user: UserEntity) => user.permission)
    public users: UserEntity[];
}