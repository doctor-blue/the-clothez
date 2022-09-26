package com.starlight.app.theclothez.home.content

import android.os.Bundle
import com.starlight.module.uicore.R
import com.starlight.module.uicore.BaseFragment
import com.starlight.module.uicore.databinding.FragmentProductListBinding

class ProductListFragment(
    val categoryId: String
) :
    BaseFragment<FragmentProductListBinding>(R.layout.fragment_product_list) {

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)

        binding {


        }
    }

    override fun initEvents() {
        super.initEvents()

    }
}