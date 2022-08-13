package com.starlight.app.theclothez.auth

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.starlight.app.theclothez.auth.adapters.ChooseAccountAdapter
import com.starlight.module.uicore.BaseFragment
import com.starlight.module.uicore.databinding.FragmentChooseAccountBinding

class ChooseAccountFragment :
    BaseFragment<FragmentChooseAccountBinding>(com.starlight.module.uicore.R.layout.fragment_choose_account) {

    private val chooseAccountAdapter by lazy {
        ChooseAccountAdapter()
    }

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)

        binding.rvChooseAccount.layoutManager = LinearLayoutManager(requireContext())
        binding.rvChooseAccount.setHasFixedSize(false)
        binding.rvChooseAccount.adapter = chooseAccountAdapter

    }

}