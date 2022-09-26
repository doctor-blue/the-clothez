package com.starlight.module.uicore

import android.app.Application
import android.content.Intent
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

abstract class SApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (isShowCrashReport()) {
            Thread.setDefaultUncaughtExceptionHandler { _, e -> // Get the stack trace.
                val sw = StringWriter()
                val pw = PrintWriter(sw)
                e.printStackTrace(pw)

                val intent = Intent(this, getActivity())
                intent.putExtra(CrashNoticeActivity.CRASH_MESSAGE, sw.toString())
                intent.putExtra(CrashNoticeActivity.IS_DEBUG_MODE, isDebugMode())
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                exitProcess(10)
            }
        }
    }

    open fun isShowCrashReport() = true
    open fun isDebugMode() = true
    abstract fun getActivity(): Class<*>

}