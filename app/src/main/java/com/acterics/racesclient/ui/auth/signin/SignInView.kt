package com.acterics.racesclient.ui.auth.signin

import android.support.annotation.StringRes
import com.acterics.racesclient.ui.base.BaseMvpNavigationView

/**
 * Created by root on 29.09.17.
 */
interface SignInView: BaseMvpNavigationView {
    fun showEmailInputError(@StringRes errorRes: Int)
    fun showPasswordInputError(@StringRes errorRes: Int)
}