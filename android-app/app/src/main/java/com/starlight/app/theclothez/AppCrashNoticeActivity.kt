package com.starlight.app.theclothez

import com.starlight.app.theclothez.home.HomeActivity
import com.starlight.module.uicore.CrashNoticeActivity

class AppCrashNoticeActivity : CrashNoticeActivity() {
    override fun getActivity(): Class<*> = HomeActivity::class.java
}