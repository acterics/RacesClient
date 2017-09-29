package com.acterics.racesclient.utils

import android.content.Context

/**
 * Created by root on 27.09.17.
 */
fun Context.getStatusBarSize(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}