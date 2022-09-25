package com.starlight.app.theclothez.auth.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.starlight.app.theclothez.auth.adapters.entity.Equatable
import com.starlight.app.theclothez.auth.adapters.entity.ExistingAccountItem
import com.starlight.app.theclothez.auth.adapters.entity.NonExistingAccountItem
import com.starlight.app.theclothez.auth.adapters.view_holders.LoginToExistingAccountViewHolder
import com.starlight.app.theclothez.auth.adapters.view_holders.LoginToOtherAccountViewHolder
import com.starlight.app.theclothez.others.Constant.CREATE_NEW_ACCOUNT_TYPE
import com.starlight.app.theclothez.others.Constant.LOGIN_AS_GUEST_TYPE
import com.starlight.app.theclothez.others.Constant.LOGIN_TO_EXISTING_ACCOUNT_TYPE
import com.starlight.module.uicore.databinding.ItemExistingAccountBinding
import com.starlight.module.uicore.databinding.ItemNonExistingAccountBinding

class ChooseAccountAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Equatable>() {
        override fun areItemsTheSame(
            oldItem: Equatable,
            newItem: Equatable
        ) = oldItem.viewType() == newItem.viewType()

        override fun areContentsTheSame(
            oldItem: Equatable,
            newItem: Equatable
        ) = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOGIN_TO_EXISTING_ACCOUNT_TYPE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemExistingAccountBinding.inflate(inflater, parent, false)
                LoginToExistingAccountViewHolder(binding)
            }
            CREATE_NEW_ACCOUNT_TYPE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemNonExistingAccountBinding.inflate(inflater, parent, false)
                LoginToOtherAccountViewHolder(binding)
            }
            LOGIN_AS_GUEST_TYPE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemNonExistingAccountBinding.inflate(inflater, parent, false)
                LoginToOtherAccountViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Cannot create viewHolder for itemViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LoginToExistingAccountViewHolder -> {
                val item = differ.currentList[position] as ExistingAccountItem
                holder.bind(item)
            }
            is LoginToOtherAccountViewHolder -> {
                val item = differ.currentList[position] as NonExistingAccountItem
                holder.onBind(item)
            }
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int {
        return differ.currentList[position].viewType()
    }

    override fun getItemCount() = differ.currentList.size


}