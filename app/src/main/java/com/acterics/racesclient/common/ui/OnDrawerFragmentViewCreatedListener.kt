package com.acterics.racesclient.common.ui

import com.acterics.racesclient.common.ui.ToolbarHolder
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 09.10.17.
 */
interface OnDrawerFragmentViewCreatedListener {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onDrawerFragmentViewCreated(toolbarHolder: ToolbarHolder, lightTheme: Boolean = false)
}