package com.starlight.app.theclothez.auth

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.starlight.module.domain.utils.DataState
import com.starlight.module.uicore.BaseFragment
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.FragmentLoginBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val authenticationViewModel: AuthenticationViewModel by activityViewModels()


    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)
        binding {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    authenticationViewModel.loginState.collect { loginState ->
                        // New value received
                        when (loginState) {
                            is DataState.Loading -> {

                            }
                            is DataState.Error -> {

                            }
                            is DataState.Success -> {

                            }
                        }
                    }
                }
            }
        }
    }

    override fun initEvents() {
        super.initEvents()
        binding {
            btnNext.setPreventDoubleClick {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                authenticationViewModel.login(email, password)
            }
        }
    }
}