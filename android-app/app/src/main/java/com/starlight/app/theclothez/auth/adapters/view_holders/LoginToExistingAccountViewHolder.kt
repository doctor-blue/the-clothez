package com.starlight.app.theclothez.auth.adapters.view_holders

import com.starlight.app.theclothez.auth.adapters.entity.ExistingAccountItem
import com.starlight.module.uicore.SViewHolder
import com.starlight.module.uicore.databinding.ItemExistingAccountBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick

class LoginToExistingAccountViewHolder(private val binding: ItemExistingAccountBinding) :
    SViewHolder<ExistingAccountItem>(binding.root) {

    fun bind(account: ExistingAccountItem) {
        binding.lbName.text = "Login to ${account.accountName}"
        binding.lbFirstChar.text = "${account.accountName.first()}"
        binding.imgAvatar.setPreventDoubleClick {
            // Login to account here
        }
    }
}