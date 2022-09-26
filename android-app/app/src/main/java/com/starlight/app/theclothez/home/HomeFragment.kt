package com.starlight.app.theclothez.home

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.starlight.app.theclothez.home.content.CategoryPagerFragment
import com.starlight.app.theclothez.home.content.ProductListFragment
import com.starlight.module.uicore.BaseFragment
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.FragmentHomeBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var categoryPagerFragment: CategoryPagerFragment
    private var tabLayoutMediator: TabLayoutMediator? = null


    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)
        binding {
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                com.starlight.module.resource.R.array.genders, R.layout.home_spinner_item
            )
            adapter.setDropDownViewResource(R.layout.home_spinner_item)
            spGender.adapter = adapter

            val fragments = listOf(
                ProductListFragment(""),
                ProductListFragment(""),
                ProductListFragment(""),
                ProductListFragment(""),
                ProductListFragment(""),
                ProductListFragment(""),
                ProductListFragment(""),
                )

            vpProduct.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            vpProduct.isUserInputEnabled = true
            categoryPagerFragment = CategoryPagerFragment(this@HomeFragment)
            categoryPagerFragment.submitList(fragments)
            vpProduct.adapter = categoryPagerFragment
            vpProduct.isSaveEnabled = false

            tabLayoutMediator = TabLayoutMediator(tlHome, vpProduct) { tab, position ->
                tab.text = "Hello $position"
            }

            tabLayoutMediator?.attach()
        }
    }

    override fun initEvents() {
        super.initEvents()
        binding {
            btnDrawer.setPreventDoubleClick {
                (requireActivity() as HomeActivity).openDrawer()
            }
            btnDropdown.setPreventDoubleClick {
                spGender.performClick()
            }
        }
    }
}