package com.starlight.app.theclothez

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.starlight.app.theclothez.auth.AuthenticationViewModel
import com.starlight.app.theclothez.auth.adapters.ChooseAccountAdapter
import com.starlight.module.uicore.BaseActivity
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.FragmentChooseAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<FragmentChooseAccountBinding>(R.layout.fragment_choose_account) {

    private val authViewModel: AuthenticationViewModel by viewModels()

    private val chooseAccountAdapter by lazy {
        ChooseAccountAdapter()
    }

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
        binding.rvChooseAccount.layoutManager = LinearLayoutManager(this)
        binding.rvChooseAccount.setHasFixedSize(false)
        binding.rvChooseAccount.adapter = chooseAccountAdapter
    }
}