package com.acterics.racesclient.ui.main

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 09.10.17.
 */
interface OnDrawerFragmentViewCreatedListener {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onDrawerFragmentViewCreated(toolbarHolder: ToolbarHolder, lightTheme: Boolean = false)
}