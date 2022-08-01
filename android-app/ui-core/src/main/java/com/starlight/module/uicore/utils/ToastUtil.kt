package com.starlight.module.uicore.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(
    messId: Int,
    time: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(this, messId, time).show()
}

fun Context.showToast(
    mess: String,
    time: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(this, mess, time).show()
}