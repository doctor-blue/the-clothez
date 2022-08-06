package com.starlight.module.domain.repository

import com.starlight.module.domain.model.Status
import com.starlight.module.domain.model.User
import com.starlight.module.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    fun emailLogin(email: String, password: String): Flow<DataState<Status>>

    fun emailSignUp(user: User, password: String): Flow<DataState<Status>>

    fun refreshToken(refreshToken: String): Flow<DataState<Status>>

    fun logout(): Flow<DataState<Status>>
}