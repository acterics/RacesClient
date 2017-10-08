package com.acterics.racesclient.ui.auth.signin

import android.support.annotation.StringRes
import com.acterics.racesclient.ui.base.BaseMvpNavigationView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 29.09.17.
 */
interface SignInView: MvpView {
    fun showEmailInputError(@StringRes errorRes: Int)
    fun hideEmailInputError()
    fun showPasswordInputError(@StringRes errorRes: Int)
    fun onViewAttached()
}