package com.acterics.racesclient.presentation.authentication.signin.view

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView

/**
 * Created by root on 29.09.17.
 */
interface SignInView: MvpView {
    fun showEmailInputError(@StringRes errorRes: Int)
    fun hideEmailInputError()
    fun showPasswordInputError(@StringRes errorRes: Int)
    fun showError(message: String?)
}