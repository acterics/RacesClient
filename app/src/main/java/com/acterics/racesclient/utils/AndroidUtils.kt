package com.acterics.racesclient.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageButton

/**
 * Created by root on 27.09.17.
 */
private object AndroidConstants {
    const val LIGHT_THRESHOLD = 0.35f
}


fun Context.getStatusBarSize(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Toolbar.getNavigationIconView(): View {
    (0..childCount)
            .map { getChildAt(it) }
            .filterIsInstance<ImageButton>()
            .forEach { return it }
    throw IllegalStateException()
}

fun View.getGlobalVisibleRect(): Rect {
    val rect = Rect()
    getGlobalVisibleRect(rect)
    return rect
}

fun <T>Bitmap.transform(transformation: (Bitmap) -> T): T {
    return transformation(this)
}

fun Int.toHSV(): FloatArray {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    return hsv
}

fun Int.isLight(): Boolean {
    return this.toHSV()[2] >= AndroidConstants.LIGHT_THRESHOLD
}