package com.acterics.racesclient.common.extentions

import android.content.Context
import android.content.SharedPreferences
import com.acterics.racesclient.common.constants.Extra
import com.acterics.racesclient.data.database.entity.User

/**
* Created by root on 27.09.17.
*/

private object AuthUtilsConstants {
    const val PREFERENCES_NAME = "com.acterics.racesclient.utils.AUTH_PREFERENCES"

}

fun Context.getAuthPreferences(): SharedPreferences {
    return getSharedPreferences(AuthUtilsConstants.PREFERENCES_NAME, Context.MODE_PRIVATE)
}

fun Context.isAuthenticate(): Boolean {
    return getAuthPreferences().getBoolean(Extra.IS_AUTH, false)
}

fun Context.getToken(): String {
    return getAuthPreferences().getString(Extra.ACCESS_TOKEN, "")
}


fun Context.saveToken(token: String) {
    getAuthPreferences().edit()
            .putString(Extra.ACCESS_TOKEN, token)
            .apply()
}


fun Context.getUser(): User {
    val prefs = getAuthPreferences()
    val firstName = prefs.getString(Extra.FIRST_NAME, "")
    val lastName = prefs.getString(Extra.LAST_NAME, "")
    val email = prefs.getString(Extra.EMAIL, "")
    val avatar = prefs.getString(Extra.AVATAR, "")
    val id = prefs.getLong(Extra.USER_ID, -1)
    return User(id, firstName, lastName, email, avatar)
}

fun Context.login(user: User) {
    getAuthPreferences()
            .edit()
            .putBoolean(Extra.IS_AUTH, true)
            .putString(Extra.FIRST_NAME, user.firstName)
            .putString(Extra.LAST_NAME, user.lastName)
            .putString(Extra.EMAIL, user.email)
            .putString(Extra.AVATAR, user.avatar)
            .putLong(Extra.USER_ID, user.id)
            .apply()
}

fun Context.logout() {
    getAuthPreferences()
            .edit()
            .clear()
            .apply()
}