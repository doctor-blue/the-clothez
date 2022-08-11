import DomainMapper from "../../domain/mappers/DomainMapper";
import User from "../../domain/model/User";
import { UserEntity } from "../entity/UserEntity";

// class UserMapper implements DomainMapper<User, UserEntity>{
//     toDomain(entity: UserEntity): User {
//         return new User(
//             entity.user_id,
//             entity.first_name,
//             entity.last_name,
//             entity.user_name,
//             entity.email,
//             entity.phone_number,
//             entity.avatar,
//             entity.gender,
//             entity.is_active,
//             entity.dob,
//             entity.permission_id
//         );
//     }
//     fromDomain(domain: User): UserEntity {
//         return new UserEntity(
//             domain.user_id,
//             domain.first_name,
//             domain.last_name,
//             domain.user_name,
//             domain.email,
//             domain.phone_number,
//             "",
//             "",
//             "",
//             domain.avatar,
//             domain.gender,
//             domain.is_active,
//             domain.dob,
//             domain.permission_id
//         )
//     }
// }