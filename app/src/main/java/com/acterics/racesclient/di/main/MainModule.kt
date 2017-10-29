package com.acterics.racesclient.di.main

import com.acterics.racesclient.data.database.AppDatabase
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.repository.RaceRepositoryImpl
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.interactor.GetRaceDetailsUseCase
import com.acterics.racesclient.domain.interactor.GetRacesUseCase
import com.acterics.racesclient.domain.repository.RaceRepository
import dagger.Module
import dagger.Provides

/**
 * Created by root on 29.10.17.
 */
@Module
class MainModule {

    @MainScope
    @Provides
    fun provideGetRaceDetailsUseCase(raceRepository: RaceRepository, scheduler: ExecutionScheduler) =
            GetRaceDetailsUseCase(raceRepository, scheduler)

    @MainScope
    @Provides
    fun provideGetRacesUseCase(raceRepository: RaceRepository, scheduler: ExecutionScheduler) =
            GetRacesUseCase(raceRepository, scheduler)

}