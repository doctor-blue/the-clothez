import DomainMapper from "../../domain/mappers/DomainMapper";
import User from "../../domain/model/User";

class UserMapper implements DomainMapper<User, UserEntity>{
    toDomain(entity: UserEntity): User {
        return new User(
            entity.userId,
            entity.firstName,
            entity.lastName,
            entity.userName,
            entity.email,
            entity.phoneNumber,
            entity.avatar,
            entity.gender,
            entity.isActive,
            entity.dob,
            entity.permisstionId
        );
    }
    fromDomain(domain: User): UserEntity {
        return new UserEntity(
            domain.user_id,
            domain.first_name,
            domain.last_name,
            domain.user_name,
            domain.email,
            domain.phone_number,
            "",
            "",
            "",
            domain.avatar,
            domain.gender,
            domain.is_active,
            domain.dob,
            domain.permission_id
        )
    }
}