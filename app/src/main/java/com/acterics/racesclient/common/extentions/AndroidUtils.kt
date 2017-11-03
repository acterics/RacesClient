package com.acterics.racesclient.common.extentions

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewCompat
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

fun Context.getSupportDrawable(@DrawableRes drawableRes: Int, theme: Resources.Theme? = null): Drawable? {
    return ResourcesCompat.getDrawable(resources, drawableRes, theme)
}

fun Toolbar.getNavigationIconView(): View {
    (0..childCount)
            .map { getChildAt(it) }
            .filterIsInstance<ImageButton>()
            .forEach { return it }
    throw IllegalStateException()
}

fun <T> List<T>.getPage(skip: Int, count: Int): List<T> {
    val page: List<T>
    val actualCount: Int
    if (skip >= size) {
        page = listOf()
    } else  {
        actualCount = if (skip + count >= size) { size - skip } else { count }
        page = subList(skip, skip + actualCount)
    }
    return page
}

fun View.getGlobalVisibleRect(): Rect {
    val rect = Rect()
    getGlobalVisibleRect(rect)
    return rect
}

fun View.setSupportTranslationName(translationName: String) {
    ViewCompat.setTransitionName(this, translationName)
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