package com.acterics.racesclient.presentation.schedule.view

import com.acterics.racesclient.common.ui.PagingMvpView
import com.acterics.racesclient.presentation.schedule.ScheduleItem

/**
 * Created by root on 15.10.17.
 */
interface ScheduleView: PagingMvpView {

    fun showRaces(races: List<ScheduleItem>)

}