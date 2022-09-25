package com.starlight.app.theclothez.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.shape.CornerFamily
import com.starlight.module.uicore.BaseActivity
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.ActivityHomeBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick


class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val adapter: DrawerMenuAdapter by lazy {
        DrawerMenuAdapter {

        }
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

            btnTest.setPreventDoubleClick {
                mainRoot.openDrawer(navView)
            }
        }
    }
}