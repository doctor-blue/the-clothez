package com.starlight.app.theclothez.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.shape.CornerFamily
import com.starlight.module.uicore.BaseActivity
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.ActivityHomeBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val adapter: DrawerMenuAdapter by lazy {
        DrawerMenuAdapter(onMenuItemClick)
    }

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)
        binding {
            rvDrawerMenu.layoutManager = LinearLayoutManager(this@HomeActivity)
            rvDrawerMenu.adapter = adapter
            adapter.submitList(DrawerMenuItem.menuItems)
            val radius = resources.getDimension(com.starlight.module.resource.R.dimen._space18dp)
            imgAvatar.shapeAppearanceModel = imgAvatar.shapeAppearanceModel
                .toBuilder()
                .setAllCornerSizes(radius)
                .build()
        }
    }

    fun openDrawer() {
        binding {
            mainRoot.openDrawer(navView)
        }
    }

    fun closeDrawer() {
        binding {
            mainRoot.closeDrawer(navView)
        }
    }

    private val onMenuItemClick: (DrawerMenuItem) -> Unit = {
        when (it.id) {
            DrawerMenuItem.HOME -> {

            }
            DrawerMenuItem.SAVED_ITEMS -> {

            }
            DrawerMenuItem.APP_SETTING -> {

            }
            DrawerMenuItem.HELP -> {

            }
        }
    }
}