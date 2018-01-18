package com.acterics.racesclient.di.app

import android.content.Context
import com.acterics.domain.repository.RaceRepository
import com.acterics.domain.repository.UserRepository
import com.acterics.racesclient.data.database.AppDatabase
import com.acterics.racesclient.data.mapper.*
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.repository.RaceRepositoryImpl
import com.acterics.racesclient.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by root on 30.10.17.
 */
@Module
open class DataModule {

    @Provides
    @Singleton
    fun provideRaceRepository(apiService: ApiService, appDatabase: AppDatabase, raceMapper: RaceMapper): RaceRepository =
            RaceRepositoryImpl(apiService, appDatabase, raceMapper)


    @Provides
    @Singleton
    fun provideUserRepository(appDatabase: AppDatabase, apiService: ApiService, context: Context, betMapper: BetMapper): UserRepository =
            UserRepositoryImpl(appDatabase, apiService, context, betMapper)

    @Provides
    @Singleton
    fun provideHorseMapper(): HorseMapper = HorseMapper()

    @Provides
    @Singleton
    fun provideOrganizationMapper(): OrganizationMapper = OrganizationMapper()

    @Provides
    @Singleton
    fun provideBetMapper(): BetMapper = BetMapper()

    @Provides
    @Singleton
    fun provideParticipationMapper(horseMapper: HorseMapper, betMapper: BetMapper): ParticipantMapper =
            ParticipantMapper(horseMapper, betMapper)

    @Provides
    @Singleton
    fun provideRaceMapper(participantMapper: ParticipantMapper,
                          organizationMapper: OrganizationMapper): RaceMapper =
            RaceMapper(participantMapper, organizationMapper)

}