package com.acterics.racesclient.utils

import android.content.Context
import android.content.SharedPreferences

/**
* Created by root on 27.09.17.
*/

object Constants {
    const val PREFERENCES_NAME = "com.acterics.racesclient.utils.AUTH_PREFERENCES"
    const val EXTRA_IS_AUTH = "com.acterics.racesclient.utils.EXTRA_IS_AUTH"
}

fun Context.getAuthPreferences(): SharedPreferences {
    return getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)
}

fun Context.isAuthenticate(): Boolean {
    return getAuthPreferences().getBoolean(Constants.EXTRA_IS_AUTH, false)
}

fun Context.getUser() {

}

fun Context.login() {
    getAuthPreferences()
            .edit()
            .putBoolean(Constants.EXTRA_IS_AUTH, true)
            .apply()
}

fun Context.logout() {
    getAuthPreferences()
            .edit()
            .putBoolean(Constants.EXTRA_IS_AUTH, false)
            .apply()
}