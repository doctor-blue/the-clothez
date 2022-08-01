package com.starlight.module.uicore

import android.Manifest
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.starlight.module.uicore.utils.PermissionUtils


abstract class BaseFragmentActivity<T : ViewDataBinding> constructor(
    @LayoutRes val layoutId: Int
) : FragmentActivity(), Initialization {

    val commonViewModel: CommonViewModel by viewModels()

    /**
     * [DataBindingComponent] is generatved during compilation
     */
    protected var bindingComponent: DataBindingComponent? = DataBindingUtil.getDefaultComponent()

    /** A backing field for providing an immutable [binding] property.  */
    private var _binding: T? = null

    /**
     * A data-binding property will be initialized in [onCreateView].
     * And provide the inflated view which depends on [layoutId].
     */
    protected val binding: T
        get() = checkNotNull(_binding) {
            "Fragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    protected inline fun binding(block: T.() -> Unit): T {
        return binding.apply(block)
    }

    open fun onConnect(isAuthenticated: Boolean) {
        if (isAuthenticated) onSetViewInfo()
    }

    open fun onDisconnect() {

    }

    protected open fun onSetViewInfo() {}

    private val listPermission = listOf(
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    /**
     * Inflate [layoutId]
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId, bindingComponent)
        listPermission.forEach {
            PermissionUtils.checkPermission(this, it, {}, {})
        }
        initControls(savedInstanceState)
        initEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null

    }
}