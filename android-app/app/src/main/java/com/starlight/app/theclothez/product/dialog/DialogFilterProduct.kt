package com.starlight.app.theclothez.product.dialog

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.starlight.app.theclothez.R
import com.starlight.app.theclothez.databinding.DialogProductFilterBinding
import com.starlight.module.uicore.SDialog
import com.starlight.module.uicore.utils.setPreventDoubleClick


class DialogFilterProduct(context: Context) :
    SDialog<DialogProductFilterBinding>(context, R.layout.dialog_product_filter) {

    override fun onDialogShown() {
        super.onDialogShown()

    }

    override fun initStates() {
        binding {
            btnBack.setPreventDoubleClick {
                onDismissAndRemoveRes()
            }
            btnApply.setPreventDoubleClick {

            }
            txtSizeXs.setPreventDoubleClick {
                sizeSelected("XS")
            }
            txtSizeS.setPreventDoubleClick {
                sizeSelected("S")
            }
            txtSizeM.setPreventDoubleClick {
                sizeSelected("M")
            }
            txtSizeL.setPreventDoubleClick {
                sizeSelected("L")
            }
            txtSizeXl.setPreventDoubleClick {
                sizeSelected("XL")
            }
        }
    }

    private fun sizeSelected(size: String) {
        binding {
            val padding: Int =
                context.resources.getDimensionPixelOffset(com.starlight.module.resource.R.dimen._space20dp)

            sizeResource.keys.forEach {
                val textView = sizeResource[it]
                val lp = textView?.layoutParams as LinearLayout.LayoutParams?
                textView?.setPadding(padding, padding, padding, padding)
                if (it == size) {
                    textView?.setTextColor(
                        ContextCompat.getColor(
                            context,
                            com.starlight.module.resource.R.color.white
                        )
                    )
                    textView?.setBackgroundResource(com.starlight.module.resource.R.drawable.bg_navi_rounder_10)
                } else {
                    textView?.setTextColor(
                        ContextCompat.getColor(
                            context,
                            com.starlight.module.resource.R.color.navy
                        )
                    )
                    textView?.setBackgroundResource(com.starlight.module.resource.R.drawable.bg_common_btn)
                }
            }
        }
    }

    private val sizeResource: HashMap<String, TextView> = hashMapOf(
        "XS" to binding.txtSizeXs,
        "S" to binding.txtSizeS,
        "M" to binding.txtSizeM,
        "L" to binding.txtSizeL,
        "XL" to binding.txtSizeXl
    )

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initBinding(): DialogProductFilterBinding =
        DialogProductFilterBinding.bind(contentView)
}