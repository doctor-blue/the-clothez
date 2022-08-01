package com.starlight.module.uicore.utils

import java.text.DecimalFormat

fun Double.decimalFormat(): String =
    if (this == this.toInt().toDouble()) this.toInt().toString() else DecimalFormat("0.00").format(
        this
    )

fun Float.decimalFormat(): String =
    if (this == this.toInt().toFloat()) this.toInt().toString() else DecimalFormat("0.00").format(
        this
    )


fun IntArray.min(): Int {
    if (size <= 0) throw IndexOutOfBoundsException("Size < 0")
    var minValue = get(0)
    for (value in this) {
        if (minValue > value) minValue = value
    }
    return minValue
}

fun IntArray.max(): Int {
    if (size <= 0) throw IndexOutOfBoundsException("Size < 0")
    var maxValue = get(0)
    for (value in this) {
        if (maxValue < value) maxValue = value
    }
    return maxValue
}
