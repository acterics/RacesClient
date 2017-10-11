package com.acterics.racesclient.utils

import android.content.Context
import android.content.SharedPreferences
import com.acterics.racesclient.data.entity.User

/**
* Created by root on 27.09.17.
*/

private object AuthUtilsConstants {
    const val PREFERENCES_NAME = "com.acterics.racesclient.utils.AUTH_PREFERENCES"
    const val EXTRA_IS_AUTH = "com.acterics.racesclient.utils.EXTRA_IS_AUTH"
    const val EXTRA_FIRST_NAME = "com.acterics.racesclient.utils.EXTRA_FIRST_NAME"
    const val EXTRA_LAST_NAME = "com.acterics.racesclient.utils.EXTRA_LAST_NAME"
    const val EXTRA_AVATAR = "com.acterics.racesclient.utils.EXTRA_AVATAR"
    const val EXTRA_EMAIL = "com.acterics.racesclient.utils.EXTRA_EMAIL"
}

fun Context.getAuthPreferences(): SharedPreferences {
    return getSharedPreferences(AuthUtilsConstants.PREFERENCES_NAME, Context.MODE_PRIVATE)
}

fun Context.isAuthenticate(): Boolean {
    return getAuthPreferences().getBoolean(AuthUtilsConstants.EXTRA_IS_AUTH, false)
}

fun Context.getUser(): User {
    val prefs = getAuthPreferences()
    val firstName = prefs.getString(AuthUtilsConstants.EXTRA_FIRST_NAME, "")
    val lastName = prefs.getString(AuthUtilsConstants.EXTRA_LAST_NAME, "")
    val email = prefs.getString(AuthUtilsConstants.EXTRA_EMAIL, "")
    val avatar = prefs.getString(AuthUtilsConstants.EXTRA_AVATAR, "")
    return User(firstName, lastName, email, avatar)
}

fun Context.login(user: User) {
    getAuthPreferences()
            .edit()
            .putBoolean(AuthUtilsConstants.EXTRA_IS_AUTH, true)
            .putString(AuthUtilsConstants.EXTRA_FIRST_NAME, user.firstName)
            .putString(AuthUtilsConstants.EXTRA_LAST_NAME, user.lastName)
            .putString(AuthUtilsConstants.EXTRA_EMAIL, user.email)
            .putString(AuthUtilsConstants.EXTRA_AVATAR, user.avatar)
            .apply()
}

fun Context.logout() {
    getAuthPreferences()
            .edit()
            .clear()
            .apply()
}