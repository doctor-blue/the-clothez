package com.starlight.app.theclothez.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.starlight.module.uicore.SViewHolder
import com.starlight.module.uicore.databinding.DrawerMenuItemBinding

class DrawerMenuAdapter(
    private val onItemClick: (DrawerMenuItem) -> Unit
) : ListAdapter<DrawerMenuItem, DrawerMenuAdapter.ViewHolder>(diff) {
    companion object {
        private val diff = object : DiffUtil.ItemCallback<DrawerMenuItem>() {
            override fun areItemsTheSame(
                oldItem: DrawerMenuItem,
                newItem: DrawerMenuItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DrawerMenuItem,
                newItem: DrawerMenuItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ViewHolder(
        private val binding: DrawerMenuItemBinding
    ) : SViewHolder<DrawerMenuItem>(binding.root) {
        override fun onBind(item: DrawerMenuItem) {
            super.onBind(item)
            binding.imgMenu.setImageResource(item.icon)
            binding.txtMenu.setText(item.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DrawerMenuItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), onItemClick)
    }
}