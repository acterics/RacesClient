package com.acterics.racesclient.di.main

import com.acterics.racesclient.presentation.main.view.MainActivity
import com.acterics.racesclient.ui.profile.ProfilePresenter
import com.acterics.racesclient.ui.race.RaceDetailPresenter
import com.acterics.racesclient.ui.schedule.SchedulePresenter
import dagger.Subcomponent

/**
 * Created by root on 29.10.17.
 */
@MainScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(profileScreen: ProfilePresenter)
    fun inject(scheduleScreen: SchedulePresenter)
    fun inject(raceDetailScreen: RaceDetailPresenter)
}