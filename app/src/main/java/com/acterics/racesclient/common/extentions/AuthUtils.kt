package com.acterics.racesclient.common.extentions

import android.content.Context
import android.content.SharedPreferences
import com.acterics.domain.model.Token
import com.acterics.domain.model.User
import com.acterics.racesclient.common.constants.Extra
import com.acterics.racesclient.exception.NoSavedUserException

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


fun Context.saveToken(token: Token) {
    getAuthPreferences().edit()
            .putString(Extra.ACCESS_TOKEN, token.token)
            .apply()
}


@Throws(NoSavedUserException::class)
fun Context.getUser(): User {

    val prefs = getAuthPreferences()

    val id = prefs.getLong(Extra.USER_ID, -1)
    if (id == -1L) {
        throw NoSavedUserException()
    }

    val firstName = prefs.getString(Extra.FIRST_NAME, null) ?: throw NoSavedUserException()
    val lastName = prefs.getString(Extra.LAST_NAME, null) ?: throw NoSavedUserException()
    val email = prefs.getString(Extra.EMAIL, null) ?: throw NoSavedUserException()
    val avatar = prefs.getString(Extra.AVATAR, "")

    return User(id, firstName, lastName, email, avatar)
}

fun Context.saveUser(user: User) {
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

fun Context.clearUser() {
    getAuthPreferences()
            .edit()
            .clear()
            .apply()
}