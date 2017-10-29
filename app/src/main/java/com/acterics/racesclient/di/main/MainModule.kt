package com.acterics.racesclient.di.main

import com.acterics.racesclient.data.AppDatabase
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.interactor.GetRaceDetails
import com.acterics.racesclient.domain.interactor.GetRaces
import dagger.Module
import dagger.Provides

/**
 * Created by root on 29.10.17.
 */
@Module
class MainModule {

    @MainScope
    @Provides
    fun provideGetRaceDetailsUseCase(apiService: ApiService, appDatabase: AppDatabase, scheduler: ExecutionScheduler) =
            GetRaceDetails(apiService, appDatabase, scheduler)

    @MainScope
    @Provides
    fun provideGetRacesUseCase(apiService: ApiService, appDatabase: AppDatabase, scheduler: ExecutionScheduler) =
            GetRaces(apiService, appDatabase, scheduler)
}