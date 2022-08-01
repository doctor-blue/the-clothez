package com.starlight.module.uicore

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.starlight.module.uicore.utils.setPreventDoubleClick

abstract class SViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    open var onItemClick: (T) -> Unit = {}
    protected var currentItem: T? = null
    protected var currentPosition: Int = -1

    init {
        view.setPreventDoubleClick {
            currentItem?.let(onItemClick)
        }
    }

    open fun onBind(item: T) {
        currentItem = item
    }

    open fun onBind(item: T, onItemClick: (T) -> Unit) {
        this.onItemClick = onItemClick
        onBind(item)
    }

    open fun onBind(item: T, position: Int, onItemClick: (T) -> Unit) {
        currentPosition = position
        onBind(item, onItemClick)
    }

    open fun onBind(item: T, position: Int) {
        onBind(item, position, onItemClick)
    }

}