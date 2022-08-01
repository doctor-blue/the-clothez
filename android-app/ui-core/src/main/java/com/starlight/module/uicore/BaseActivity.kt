package com.starlight.module.uicore

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.starlight.module.uicore.utils.PermissionUtils


abstract class BaseActivity<T : ViewDataBinding> constructor(
    @LayoutRes val layoutId: Int
) : AppCompatActivity(), Initialization {

    val commonViewModel: CommonViewModel by viewModels()
    lateinit var navController: NavController

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
    val binding: T
        get() = checkNotNull(_binding) {
            "Fragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    inline fun binding(block: T.() -> Unit): T {
        return binding.apply(block)
    }

    open fun onConnect(isAuthenticated: Boolean) {
        if (isAuthenticated) onSetViewInfo()
    }

    open fun onDisconnect() {

    }

    protected open fun onSetViewInfo() {}

    private val listPermission: List<String>
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            )
        else
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE
            )


    /**
     * Inflate [layoutId]
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId, bindingComponent)
//        listPermission.forEach {
//            PermissionUtils.checkPermission(this, it, {}, {})
//        }
        PermissionUtils.checkPermissions(this, listPermission, {}, {})
        initControls(savedInstanceState)
        initEvents()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null
    }

    private val TAG = "BASE_ACTIVITY"
    fun safeNav(navDirections: NavDirections) {
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_RESUME) {
                    lifecycle.removeObserver(this)
                    try {
                        navController.navigate(navDirections)
                    } catch (e: Exception) {
                        Log.e(TAG, "safeNav: ${e.message}")
                    }
                }
            }
        })
    }
}