package com.acterics.racesclient.presentation.schedule.view

import com.acterics.racesclient.common.ui.PagingMvpView
import com.acterics.racesclient.presentation.schedule.ScheduleItem
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 15.10.17.
 */
interface ScheduleView: PagingMvpView {

    fun showRaces(races: List<ScheduleItem>)


}