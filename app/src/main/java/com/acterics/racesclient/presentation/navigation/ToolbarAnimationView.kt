package com.acterics.racesclient.presentation.navigation

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 11.11.17.
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface ToolbarAnimationView : BaseToolbarAnimationView {
    fun startBackToolbarAnimation()
}