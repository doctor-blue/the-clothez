package com.starlight.app.theclothez

import com.starlight.module.uicore.CrashNoticeActivity

class AppCrashNoticeActivity : CrashNoticeActivity() {
    override fun getActivity(): Class<*> = MainActivity::class.java
}