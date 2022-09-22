package com.starlight.app.theclothez.auth.adapters.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.starlight.app.theclothez.auth.adapters.adapter_models.ExistingAccountItem
import com.starlight.module.uicore.databinding.ItemExistingAccountBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick

class LoginToExistingAccountViewHolder(private val binding: ItemExistingAccountBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(account: ExistingAccountItem) {
        binding.lbName.text = "Login to ${account.accountName}"
        binding.lbFirstChar.text = "${account.accountName.first()}"
        binding.imgAvatar.setPreventDoubleClick {
            // Login to account here
        }
    }
}