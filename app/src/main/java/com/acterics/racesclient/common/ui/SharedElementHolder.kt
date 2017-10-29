package com.acterics.racesclient.common.ui

import android.view.View

/**
 * Created by root on 15.10.17.
 */
interface SharedElementHolder {
    fun getSharedView(): View?
    fun getSharedTranslationName(): String?
}