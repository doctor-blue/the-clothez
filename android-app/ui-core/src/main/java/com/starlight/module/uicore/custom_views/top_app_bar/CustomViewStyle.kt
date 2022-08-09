package com.starlight.module.uicore.custom_views.top_app_bar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

interface CustomViewStyle {
    fun inflateLayout(context: Context, layoutId: Int, viewGroup: ViewGroup) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(layoutId, viewGroup)
    }
    fun initViews()
    fun initAttributes()
    interface ClickEvent {
        fun initEvents()
    }
}