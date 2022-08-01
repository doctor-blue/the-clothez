package com.starlight.module.uicore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.starlight.module.uicore.databinding.ActivityCrashNoticeBinding
import com.starlight.module.uicore.utils.gone
import com.starlight.module.uicore.utils.setPreventDoubleClick
import com.starlight.module.uicore.utils.show

abstract class CrashNoticeActivity : AppCompatActivity(), Initialization {

    companion object {
        const val CRASH_MESSAGE = "CRASH_MESSAGE"
        const val IS_DEBUG_MODE = "CRASH_IS_DEBUG_MODE"
    }

    /**
     * [DataBindingComponent] is generatved during compilation
     */
    protected var bindingComponent: DataBindingComponent? = DataBindingUtil.getDefaultComponent()

    /** A backing field for providing an immutable [binding] property.  */
    private var _binding: ActivityCrashNoticeBinding? = null

    /**
     * A data-binding property will be initialized in [onCreateView].
     */
    protected val binding: ActivityCrashNoticeBinding
        get() = checkNotNull(_binding) {
            "Fragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    protected inline fun binding(block: ActivityCrashNoticeBinding.() -> Unit): ActivityCrashNoticeBinding {
        return binding.apply(block)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =
            DataBindingUtil.setContentView(this, R.layout.activity_crash_notice, bindingComponent)

        initControls(savedInstanceState)
        initEvents()
    }

    private var isShowDetail = false

    open fun onClickReport(message: String?) {
        onReportComplete()
    }

    open fun onReportComplete() {
        restartApplication()
    }

    abstract fun getActivity(): Class<*>

    open fun restartApplication() {
        val intent = Intent(this, getActivity())
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.component?.let {
            intent.action = Intent.ACTION_MAIN
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
        }
        startActivity(intent)
    }

    open fun getCustomBannerDrawable(): Int = R.drawable.ic_bug_notice

    override fun initControls(savedInstanceState: Bundle?) {
        super.initControls(savedInstanceState)
        val message = intent.getStringExtra(CRASH_MESSAGE)
        val isDebugMode = intent.getBooleanExtra(IS_DEBUG_MODE, false)
        binding {
            imgBanner.setImageResource(getCustomBannerDrawable())
            txtDetail.text = message
            btnRestart.setPreventDoubleClick {
                onClickReport(message)
                restartApplication()
            }
            if (isDebugMode) {
                btnShowDetail.setPreventDoubleClick {
                    if (isShowDetail)
                        txtDetail.gone()
                    else
                        txtDetail.show()
                    isShowDetail = !isShowDetail
                }
            }else btnShowDetail.gone()
        }

        Log.e(application.packageName, message.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null
    }


}