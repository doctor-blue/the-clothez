package com.starlight.app.theclothez.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starlight.module.domain.model.Status
import com.starlight.module.domain.repository.AuthenticationRepository
import com.starlight.module.domain.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel
@Inject constructor(
    private val authRepo: AuthenticationRepository
) : ViewModel() {

    private val _loginState: MutableStateFlow<DataState<Status>> =
        MutableStateFlow(DataState.Loading())

    val loginState: StateFlow<DataState<Status>> = _loginState

    fun login(
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            authRepo.emailLogin(email, password).onEach {
                _loginState.value = it
            }.collect()
        }
    }
}