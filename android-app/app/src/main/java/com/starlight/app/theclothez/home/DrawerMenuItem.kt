package com.starlight.app.theclothez.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.starlight.module.resource.R

class DrawerMenuItem(
    @StringRes  val title: Int,
    @DrawableRes  val icon: Int,
     val id: Int
) {

    override fun equals(other: Any?): Boolean {
        if (other !is DrawerMenuItem)
            return super.equals(other)
        return this.id == other.id
    }

    override fun hashCode(): Int {
        var result = title
        result = 31 * result + icon
        result = 31 * result + id
        return result
    }

    companion object {
        const val HOME = 1
        const val SAVED_ITEMS = 2
        const val APP_SETTING = 3
        const val HELP = 4

        val menuItems = listOf(
            DrawerMenuItem(
                R.string.home,
                R.drawable.ic_home,
                HOME
            ),
            DrawerMenuItem(
                R.string.saved_items,
                R.drawable.ic_heart,
                SAVED_ITEMS
            ),
            DrawerMenuItem(
                R.string.app_settings,
                R.drawable.ic_settings,
                APP_SETTING
            ),
            DrawerMenuItem(
                R.string.help_faqs,
                R.drawable.ic_info,
                HELP
            ),
        )

    }
}