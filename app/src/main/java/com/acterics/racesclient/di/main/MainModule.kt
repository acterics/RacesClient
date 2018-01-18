package com.acterics.racesclient.di.main

import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import com.acterics.domain.repository.RaceRepository
import com.acterics.domain.repository.UserRepository
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.PagingMvpViewDelegate
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.interactor.AddBetUseCase
import com.acterics.racesclient.domain.interactor.GetRaceDetailsUseCase
import com.acterics.racesclient.domain.interactor.GetRacesUseCase
import com.acterics.racesclient.presentation.main.view.MainActivity
import dagger.Module
import dagger.Provides

/**
 * Created by root on 29.10.17.
 */
@Module
class MainModule(val mainActivity: MainActivity) {


    @MainScope
    @Provides
    fun provideToolbar(): Toolbar =
            mainActivity.findViewById(R.id.toolbar)

    @MainScope
    @Provides
    fun provideNavigationView(): NavigationView =
            mainActivity.findViewById(R.id.vNavigationDrawer)

    @MainScope
    @Provides
    fun provideDrawerHolder(): DrawerLayout =
            mainActivity.findViewById(R.id.holderDrawer)


//    @MainScope
//    @Provides
//    fun provideActionBarDrawerToggleBuilder(): ActionBarToggleBinder =
//            ActionBarToggleBinder().apply {
//                activity = mainActivity
//                drawerLayout = mainActivity.findViewById(R.id.holderDrawer)
//            }

    @MainScope
    @Provides
    fun provideGetRaceDetailsUseCase(raceRepository: RaceRepository, scheduler: ExecutionScheduler) =
            GetRaceDetailsUseCase(raceRepository, scheduler)

    @MainScope
    @Provides
    fun provideGetRacesUseCase(raceRepository: RaceRepository, scheduler: ExecutionScheduler) =
            GetRacesUseCase(raceRepository, scheduler)

    @MainScope
    @Provides
    fun provideConfirmBetUseCase(userRepository: UserRepository, scheduler: ExecutionScheduler) =
            AddBetUseCase(userRepository, scheduler)

    @MainScope
    @Provides
    fun providePagingViewDelegate(): PagingMvpViewDelegate = PagingMvpViewDelegate()

}