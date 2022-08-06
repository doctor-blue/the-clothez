package com.starlight.module.data.datasource.mappers

import com.starlight.module.data.datasource.entity.UserEntity
import com.starlight.module.domain.mappers.DomainMapper
import com.starlight.module.domain.model.User
import com.starlight.module.domain.utils.Constant
import java.util.*

class UserMapper : DomainMapper<UserEntity, User> {
    override fun toDomain(entity: UserEntity): User {
        return User(
            entity.userName,
            entity.userId,
            entity.firstName,
            entity.lastName,
            entity.email,
            entity.phoneNumber,
            entity.isActive,
            entity.avatar ?: "",
            entity.gender,
            Date(),
            User.NORMAL_USER
        )
    }

    override fun fromDomain(model: User): UserEntity {
        return UserEntity(
            model.userName,
            model.userId,
            model.firstName,
            model.lastName,
            model.email,
            model.phoneNumber,
            model.isActive,
            model.avatar,
            model.gender,
            Constant.simpleDateFormat.format(model.dob),
            User.NORMAL_USER
        )
    }
}