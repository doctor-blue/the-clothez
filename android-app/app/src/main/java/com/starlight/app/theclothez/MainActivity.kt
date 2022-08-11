package com.starlight.app.theclothez

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import com.starlight.app.theclothez.auth.AuthenticationViewModel
import com.starlight.module.uicore.BaseActivity
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val authViewModel: AuthenticationViewModel by viewModels()

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)

        authViewModel.loginState.asLiveData().observe(this) {
            Log.d("MainActivity login", it.data?.message ?: "null")
        }

    }

    override fun initEvents() {
        super.initEvents()
        binding {
//            btnTest.setPreventDoubleClick {
//                authViewModel.login("vantan.nguyen0726@gmail.com", "nao123456")
//            }
        }
    }
}