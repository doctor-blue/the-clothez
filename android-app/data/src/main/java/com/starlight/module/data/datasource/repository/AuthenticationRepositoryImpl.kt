package com.starlight.module.data.datasource.repository

import com.starlight.module.data.datasource.cache.AuthCache
import com.starlight.module.data.datasource.mappers.UserMapper
import com.starlight.module.data.datasource.remote.AuthService
import com.starlight.module.data.datasource.remote.request.LoginRequest
import com.starlight.module.data.datasource.remote.request.RefreshTokenRequest
import com.starlight.module.data.datasource.remote.request.SignUpRequest
import com.starlight.module.domain.const.SUCCESS_CODE
import com.starlight.module.domain.model.Status
import com.starlight.module.domain.model.User
import com.starlight.module.domain.repository.AuthenticationRepository
import com.starlight.module.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val service: AuthService,
    private val userMapper: UserMapper,
    private val authCache: AuthCache,
) : AuthenticationRepository {

    override fun emailLogin(email: String, password: String): Flow<DataState<Status>> {
        return flow {
            emit(DataState.Loading(null))
            try {
                val response = service.login(LoginRequest(email, password))

                if (response.data != null) {
                    // save token cache
                    authCache.setToken(response.data)
                    emit(
                        DataState.Success(Status(SUCCESS_CODE, "Login success"))
                    )
                    return@flow
                }
                emit(
                    DataState.Error(
                        response.status.code,
                        Status(response.status.code, response.status.message)
                    )
                )
            } catch (e: Exception) {
                emit(
                    DataState.Error(-1, Status(-1, "Login error. $e"))
                )
            }

        }
    }

    override fun emailSignUp(user: User, password: String): Flow<DataState<Status>> {
        return flow {
            emit(DataState.Loading(null))
            try {
                val response = service.signUp(
                    SignUpRequest(
                        userMapper.fromDomain(user),
                        password
                    )
                )
                if (response.status.code == SUCCESS_CODE) {
                    emit(
                        DataState.Success(
                            Status(SUCCESS_CODE, response.status.message)
                        )
                    )
                    return@flow
                }
                emit(
                    DataState.Error(
                        response.status.code,
                        Status(response.status.code, response.status.message)
                    )
                )

            } catch (e: Exception) {
                emit(
                    DataState.Error(-1, Status(-1, "Signup error."))
                )
            }

        }
    }

    override fun refreshToken(refreshToken: String): Flow<DataState<Status>> {
        return flow {
            emit(DataState.Loading(null))
            try {
                val response = service.refreshToken(RefreshTokenRequest(refreshToken))

                if (response.data != null) {
                    // save token cache
                    authCache.setToken(response.data)
                    emit(
                        DataState.Success(Status(SUCCESS_CODE, "Login success"))
                    )
                    return@flow
                }
                emit(
                    DataState.Error(
                        response.status.code,
                        Status(response.status.code, response.status.message)
                    )
                )
            } catch (e: Exception) {
                emit(
                    DataState.Error(-1, Status(-1, "Refresh error."))
                )
            }
        }
    }

    override fun logout(): Flow<DataState<Status>> {
        return flow {
            try {
                authCache.setToken(null)
                emit(
                    DataState.Success(Status(SUCCESS_CODE, "Logout success"))
                )
            } catch (e: Exception) {
                emit(
                    DataState.Error(-1, Status(-1, "logout error."))
                )
            }
        }
    }
}