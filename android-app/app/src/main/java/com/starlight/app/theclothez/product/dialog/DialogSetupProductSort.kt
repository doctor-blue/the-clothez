package com.starlight.app.theclothez.product.dialog

import android.content.Context
import com.starlight.app.theclothez.R
import com.starlight.app.theclothez.databinding.DialogSetupProductSortBinding
import com.starlight.module.uicore.SDialog

class DialogSetupProductSort(context: Context) :
    SDialog<DialogSetupProductSortBinding>(context, R.layout.dialog_setup_product_sort) {

    override fun onDialogShown() {
        super.onDialogShown()

    }

    override fun initStates() {

    }

    override fun isCanceledOnTouchOutside(): Boolean = true

    override fun initBinding(): DialogSetupProductSortBinding =
        DialogSetupProductSortBinding.bind(contentView)
}