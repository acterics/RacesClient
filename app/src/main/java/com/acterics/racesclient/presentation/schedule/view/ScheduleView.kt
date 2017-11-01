package com.acterics.racesclient.presentation.schedule.view

import com.acterics.racesclient.presentation.schedule.ScheduleItem
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 15.10.17.
 */
interface ScheduleView: MvpView {


    fun showRaces(races: List<ScheduleItem>)

    @StateStrategyType(SkipStrategy::class)
    fun startScheduleLoading(isFirstPage: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun stopScheduleLoading()

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: String?)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun resetPage(page: Int)


}