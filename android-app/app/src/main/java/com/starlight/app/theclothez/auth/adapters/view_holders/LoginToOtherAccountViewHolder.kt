package com.starlight.app.theclothez.auth.adapters.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.starlight.app.theclothez.auth.adapters.adapter_models.NonExistingAccountItem
import com.starlight.app.theclothez.others.Constant.CREATE_NEW_ACCOUNT_TYPE
import com.starlight.app.theclothez.others.Constant.LOGIN_AS_GUEST_TYPE
import com.starlight.module.uicore.databinding.ItemNonExistingAccountBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick

class LoginToOtherAccountViewHolder(private val binding: ItemNonExistingAccountBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NonExistingAccountItem) {
        when (item.id) {
            LOGIN_AS_GUEST_TYPE -> {
                binding.lbName.text =
                    binding.root.resources.getString(com.starlight.module.resource.R.string.login_as_guest)
                binding.lbFirstChar.text = "G"
                binding.imgAvatar.setPreventDoubleClick {
                    // Login as Guest here
                }
            }
            CREATE_NEW_ACCOUNT_TYPE -> {
                binding.lbName.text =
                    binding.root.resources.getString(com.starlight.module.resource.R.string.create_new_account)
                binding.lbFirstChar.text = "+"
                binding.imgAvatar.setPreventDoubleClick {
                    // Login as Guest here
                }
            }
            else -> throw IllegalArgumentException("Cannot create viewHolder for itemViewType")
        }
    }
}