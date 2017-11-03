package com.acterics.racesclient.di.main

import com.acterics.racesclient.di.profile.ProfileComponent
import com.acterics.racesclient.presentation.main.view.MainActivity
import com.acterics.racesclient.presentation.profile.view.ProfileFragment
import com.acterics.racesclient.presentation.racedetails.view.RaceDetailFragment
import com.acterics.racesclient.presentation.schedule.view.ScheduleFragment
import com.acterics.racesclient.presentation.settings.SettingsFragment
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

    fun profileComponentBuilder(): ProfileComponent.Builder

    fun inject(mainActivity: MainActivity)
    fun inject(profileFragment: ProfileFragment)
    fun inject(scheduleFragment: ScheduleFragment)
    fun inject(raceDetailFragment: RaceDetailFragment)
    fun inject(settingsFragment: SettingsFragment)
}