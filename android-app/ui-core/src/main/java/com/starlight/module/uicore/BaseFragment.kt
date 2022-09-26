package com.starlight.module.uicore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.starlight.module.uicore.utils.hideKeyboard


abstract class BaseFragment<T : ViewDataBinding> constructor(
    @LayoutRes val layoutId: Int
) : Fragment(), Initialization {
    private val TAG = "BaseFragment"

    protected val commonViewModel: CommonViewModel by viewModels()

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

    protected fun checkOnTop(): Boolean {
        return navController.backQueue.size > 2
    }

    /**
     * Inflate [layoutId]
     */
    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false, bindingComponent)
        try {
            navController = findNavController()
        } catch (e: Exception) {

        }
        initControls(savedInstanceState)
        initEvents()
        if (isCustomBackPressed()) {
            activity?.onBackPressedDispatcher?.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        onBackPressed()
                    }
                })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSetViewInfo()
    }

    protected open fun onSetViewInfo() {}

    // custom on back press
    protected open fun isCustomBackPressed() = false

    protected open fun onBackPressed() {}

    protected open fun onConnect(isAuthenticated: Boolean) {}
    protected open fun onDisconnect() {}

    /**
     * Unbind
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }

    fun safeNav(@IdRes currentDestination: Int, action: Int) {
        if (navController.currentDestination?.id == currentDestination) {
            lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_RESUME) {
                        lifecycle.removeObserver(this)
                        try {
                            navController.navigate(action)
                        } catch (e: IllegalArgumentException) {
                            Log.e(TAG, "safeNav: ${e.message}")
                        }
                    }
                }
            })
            view?.hideKeyboard()
        }
    }

    fun safeNav(@IdRes currentDestination: Int, navDirections: NavDirections) {
        if (navController.currentDestination?.id == currentDestination) {
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
            view?.hideKeyboard()
        }
    }

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

    fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}