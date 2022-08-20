package com.starlight.app.theclothez

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.starlight.app.theclothez.auth.AuthenticationViewModel
import com.starlight.app.theclothez.auth.adapters.ChooseAccountAdapter
import com.starlight.app.theclothez.auth.adapters.adapter_models.Equatable
import com.starlight.app.theclothez.auth.adapters.adapter_models.ExistingAccountItem
import com.starlight.app.theclothez.auth.adapters.adapter_models.NonExistingAccountItem
import com.starlight.app.theclothez.others.Constant.CREATE_NEW_ACCOUNT_TYPE
import com.starlight.app.theclothez.others.Constant.LOGIN_AS_GUEST_TYPE
import com.starlight.app.theclothez.others.SpacesItemDecorator
import com.starlight.module.uicore.BaseActivity
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.FragmentChooseAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<FragmentChooseAccountBinding>(R.layout.fragment_choose_account) {

    private val authViewModel: AuthenticationViewModel by viewModels()

    private val adapter by lazy {
        ChooseAccountAdapter()
    }

    private val listTest by lazy {
        val list = mutableListOf<Equatable>()
        list.add(ExistingAccountItem("An"))
        list.add(ExistingAccountItem("Vu"))
        list.add(ExistingAccountItem("Tan"))
        list.add(ExistingAccountItem("An"))
        list.add(ExistingAccountItem("Vu"))
        list.add(ExistingAccountItem("Tan"))
        list.add(ExistingAccountItem("An"))
        list.add(ExistingAccountItem("Vu"))
        list.add(ExistingAccountItem("Tan"))
        list.add(NonExistingAccountItem(LOGIN_AS_GUEST_TYPE))
        list.add(NonExistingAccountItem(CREATE_NEW_ACCOUNT_TYPE))
        list
    }

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)

        setupRecyclerView()
        addingItemsToList()
    }

    private fun addingItemsToList() {
        adapter.differ.submitList(listTest)
    }


    private fun setupRecyclerView() {
        binding.rvChooseAccount.apply {
            val linearManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            layoutManager = linearManager
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            addItemDecoration(SpacesItemDecorator(80))
            adapter = this@MainActivity.adapter
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