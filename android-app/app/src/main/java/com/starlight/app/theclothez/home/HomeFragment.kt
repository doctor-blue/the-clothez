package com.starlight.app.theclothez.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.starlight.app.theclothez.home.content.CategoryPagerFragment
import com.starlight.app.theclothez.home.content.ProductListFragment
import com.starlight.module.domain.const.FEMALE
import com.starlight.module.domain.const.MALE
import com.starlight.module.domain.utils.DataState
import com.starlight.module.uicore.BaseFragment
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.FragmentHomeBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var categoryPagerFragment: CategoryPagerFragment
    private var tabLayoutMediator: TabLayoutMediator? = null

    private val homeViewModel: HomeViewModel by viewModels()


    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)

        binding {
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                com.starlight.module.resource.R.array.genders, R.layout.home_spinner_item
            )
            adapter.setDropDownViewResource(R.layout.home_spinner_item)
            spGender.adapter = adapter


            homeViewModel.getCategory(FEMALE)

            homeViewModel.categoryLiveData.observe(viewLifecycleOwner) {
                if (it is DataState.Success) {
                    it.data?.let { list ->
                        val fragments =
                            list.map { category -> ProductListFragment(category.id) }
                                .toMutableList()
                        fragments.add(0, ProductListFragment(""))

                        vpProduct.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                        vpProduct.isUserInputEnabled = true
                        vpProduct.isSaveEnabled = false

                        categoryPagerFragment = CategoryPagerFragment(this@HomeFragment)
                        categoryPagerFragment.submitList(fragments)
                        vpProduct.adapter = categoryPagerFragment

                        tabLayoutMediator = TabLayoutMediator(tlHome, vpProduct) { tab, position ->
                            if (position == 0)
                                tab.setText(com.starlight.module.resource.R.string.all)
                            else
                                tab.text = list[position-1].name
                        }

                        tabLayoutMediator?.attach()
                    }
                }


            }
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
            spGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    homeViewModel.getCategory(
                        if (position == 1)
                            MALE
                        else FEMALE
                    )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    homeViewModel.getCategory(FEMALE)
                }

            }
        }
    }
}