package com.acterics.racesclient.utils.keyboard

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 03.10.17.
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface KeyboardMvpView : MvpView {
    fun onKeyboardVisibleChanged(visible: Boolean)
}