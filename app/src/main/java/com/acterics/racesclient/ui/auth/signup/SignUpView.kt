package com.acterics.racesclient.ui.auth.signup

import com.arellomobile.mvp.MvpView

/**
 * Created by root on 02.10.17.
 */
interface SignUpView: MvpView {
    fun showError(message: String?)
}