package com.acterics.racesclient.ui.base

import android.view.View

/**
 * Created by root on 15.10.17.
 */
interface SharedElementHolder {
    fun getSharedView(): View?
    fun getSharedTranslationName(): String?
}