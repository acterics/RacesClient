package com.acterics.racesclient.utils.navigation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 12.11.17.
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface BaseToolbarAnimationView : MvpView {
    fun bindNavigationIcon()
    fun postBindNavigationIcon()
}