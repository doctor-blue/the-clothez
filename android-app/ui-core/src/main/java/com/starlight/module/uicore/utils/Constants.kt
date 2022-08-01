package com.starlight.module.uicore.utils

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val COMMON_TAG = "COMMON_TAG"
    var isDataLoaded = false
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
    val monthFormatter = SimpleDateFormat("MM/yyyy", Locale.ROOT)
    val timeFormatter = SimpleDateFormat("hh:mm", Locale.ROOT)
}