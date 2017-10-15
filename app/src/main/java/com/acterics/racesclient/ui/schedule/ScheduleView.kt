package com.acterics.racesclient.ui.schedule

import com.acterics.racesclient.data.entity.Race
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 15.10.17.
 */
interface ScheduleView: MvpView {
    fun showRaces(races: List<ScheduleItem>)
    fun startScheduleLoading()
    fun stopScheduleLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun resetScrollListener(page: Int)
}