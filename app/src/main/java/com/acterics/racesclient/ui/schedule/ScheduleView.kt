package com.acterics.racesclient.ui.schedule

import com.acterics.racesclient.ui.item.ScheduleItem
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 15.10.17.
 */
interface ScheduleView: MvpView {


    fun showRaces(races: List<ScheduleItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun startScheduleLoading(isFirstPage: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun stopScheduleLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(message: String?)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun resetPage(page: Int)


}