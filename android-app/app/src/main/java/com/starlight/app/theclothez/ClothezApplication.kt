package com.starlight.app.theclothez

import com.starlight.module.uicore.SApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClothezApplication : SApplication() {
    override fun getActivity(): Class<*> = AppCrashNoticeActivity::class.java
}