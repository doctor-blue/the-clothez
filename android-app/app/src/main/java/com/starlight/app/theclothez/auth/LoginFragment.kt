package com.starlight.app.theclothez.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.starlight.module.domain.utils.DataState
import com.starlight.module.uicore.BaseFragment
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.FragmentLoginBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick
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
                            is DataState.Success -> {
                                hideProgressBar()
                                val action =
                                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                                findNavController().navigate(action)
                            }
                            is DataState.Error -> {
                                hideProgressBar()
                                loginState.data?.let {
                                    showToast(it.message)
                                } ?: run {
                                    showToast("Something went wrong")
                                }
                            }
                            is DataState.Loading -> {
                                showProgressBar()
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

    private fun showProgressBar() {
        binding {
            pbLoading.visibility = View.VISIBLE
        }
    }

    private fun hideProgressBar() {
        binding {
            pbLoading.visibility = View.GONE
        }
    }
}