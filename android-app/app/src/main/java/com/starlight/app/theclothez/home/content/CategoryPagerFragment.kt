package com.starlight.app.theclothez.home.content

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

class CategoryPagerFragment(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    private var currentList = mutableListOf<ProductListFragment>()
    override fun getItemCount(): Int = currentList.size

    override fun createFragment(position: Int): Fragment {
        return currentList[position]
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            for (payload in payloads) {
                if (payload is Payload)
                    onBindViewHolder(holder, position)
            }
        }
    }

    fun notifyTab(uuid: String): Int {
        for ((pos, value) in currentList.withIndex()) {
            if (uuid == value.categoryId) {
                notifyItemChanged(pos, Payload())
                return pos
            }
        }
        return -1
    }

    fun notifyRemovedTab(uuid: String): Int {
        for ((pos, value) in currentList.withIndex()) {
            if (uuid == value.categoryId) {
                currentList.removeAt(pos)
                notifyItemRemoved(pos)
                return pos
            }
        }
        return -1
    }

    fun addItem(categoryId: String) {
        val pos = currentList.size - 1
        currentList.add(ProductListFragment(categoryId))
        notifyItemInserted(pos)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ProductListFragment>) {
        if (list.size != 1 || currentList.size != 1) {
            currentList = list.toMutableList()
            notifyDataSetChanged()
        }
    }


    class Payload
}