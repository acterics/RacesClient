package com.acterics.racesclient.ui.schedule

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 15.10.17.
 */
interface ScheduleView: MvpView {


    fun showRaces(races: List<ScheduleItem>)
    fun startScheduleLoading(isFirstPage: Boolean)
    fun stopScheduleLoading()
    fun showError(message: String?)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun resetPage(page: Int)


}