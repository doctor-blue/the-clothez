package com.starlight.app.theclothez.auth.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.starlight.module.uicore.databinding.ItemChooseAccountBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick

class ChooseAccountViewHolder(private val binding: ItemChooseAccountBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {

    }

    fun onBind(account: String) {
        binding.lbName.text = "Login to $account"
        binding.imgAvatar.setPreventDoubleClick {
            // Login to account here
        }
    }

    fun onBindOtherOptions(item: ChooseAccountItem) {
        binding.lbName.text = item.text
        binding.lbFirstChar.text = item.firstChar
    }

    companion object {
        fun create(
            inflater: LayoutInflater,
            parent: ViewGroup
        ): ChooseAccountViewHolder {
            val binding: ItemChooseAccountBinding =
                DataBindingUtil.inflate(
                    inflater,
                    com.starlight.module.uicore.R.layout.item_choose_account,
                    parent,
                    false
                )
            return ChooseAccountViewHolder(binding)
        }
    }
}