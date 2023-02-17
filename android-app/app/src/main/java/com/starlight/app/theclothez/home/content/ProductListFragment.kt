package com.starlight.app.theclothez.home.content

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.starlight.app.theclothez.product.dialog.DialogFilterProduct
import com.starlight.module.uicore.BaseFragment
import com.starlight.module.uicore.R
import com.starlight.module.uicore.databinding.FragmentProductListBinding
import com.starlight.module.uicore.utils.setPreventDoubleClick

class ProductListFragment(
    val categoryId: String
) :
    BaseFragment<FragmentProductListBinding>(R.layout.fragment_product_list) {
    private val productListViewModel: ProductListViewModel by viewModels()
    private val dialogFilterProduct: DialogFilterProduct by lazy {
        DialogFilterProduct(requireContext())
    }

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)
        binding {

//            productListViewModel.getProductByCategory(categoryId){
//
//            }
        }
    }

    override fun initEvents() {
        super.initEvents()
        binding {
            btnFilter.setPreventDoubleClick {
                dialogFilterProduct.show(lifecycle)
            }

        }
    }
}