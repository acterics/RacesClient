package com.acterics.racesclient.presentation.authentication.signup.view

import com.arellomobile.mvp.MvpView

/**
 * Created by root on 02.10.17.
 */
interface SignUpView: MvpView {
    fun showError(message: String?)
}