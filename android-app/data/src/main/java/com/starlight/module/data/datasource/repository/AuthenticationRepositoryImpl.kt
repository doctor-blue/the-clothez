package com.starlight.module.data.datasource.repository

import android.util.Log
import com.starlight.module.data.datasource.cache.AuthCache
import com.starlight.module.data.datasource.entity.TokenEntity
import com.starlight.module.data.datasource.mappers.UserMapper
import com.starlight.module.data.datasource.remote.AuthService
import com.starlight.module.data.datasource.remote.request.LoginRequest
import com.starlight.module.data.datasource.remote.request.RefreshTokenRequest
import com.starlight.module.data.datasource.remote.request.SignUpRequest
import com.starlight.module.data.datasource.remote.response.StatusResponse
import com.starlight.module.domain.model.Status
import com.starlight.module.domain.model.User
import com.starlight.module.domain.repository.AuthenticationRepository
import com.starlight.module.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.math.log

class AuthenticationRepositoryImpl @Inject constructor(
    private val service: AuthService,
    private val userMapper: UserMapper,
    private val authCache: AuthCache,
) : AuthenticationRepository {
    override fun emailLogin(email: String, password: String): Flow<DataState<Status>> {
        return flow {
            emit(DataState.Loading(null))
            try {
                val statusRes = service.login(LoginRequest(email, password))

                if (statusRes is TokenEntity) {
                    authCache.setToken(statusRes)
                    authCache.getToken {}.onEach {
                        Log.d("login , ", it?.refreshToken.toString())

                    }.collect()
                    emit(
                        DataState.Success(Status(200, "Login success"))
                    )
                }
//                if (statusRes is StatusResponse) {
//                    emit(
//                        DataState.Error(statusRes.code, Status(statusRes.code, statusRes.message))
//                    )
//                }
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
                val statusRes = service.signUp(
                    SignUpRequest(
                        userMapper.fromDomain(user),
                        password
                    )
                )
                emit(
                    DataState.Success(
                        Status(statusRes.code, statusRes.message)
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
                val statusRes = service.refreshToken(RefreshTokenRequest(refreshToken))

                if (statusRes is TokenEntity) {
                    authCache.setToken(statusRes)
                    emit(
                        DataState.Success(Status(200, "Login success"))
                    )
                }
                if (statusRes is StatusResponse) {
                    emit(
                        DataState.Error(statusRes.code, Status(statusRes.code, statusRes.message))
                    )
                }
            } catch (e: Exception) {
                emit(
                    DataState.Error(-1, Status(-1, "Login error."))
                )
            }
        }
    }
}