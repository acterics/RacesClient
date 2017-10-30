package com.acterics.racesclient.di.app

import android.content.Context
import com.acterics.racesclient.data.database.AppDatabase
import com.acterics.racesclient.data.mapper.HorseMapper
import com.acterics.racesclient.data.mapper.OrganizationMapper
import com.acterics.racesclient.data.mapper.ParticipantMapper
import com.acterics.racesclient.data.mapper.RaceMapper
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.repository.RaceRepositoryImpl
import com.acterics.racesclient.data.repository.UserRepositoryImpl
import com.acterics.racesclient.domain.repository.RaceRepository
import com.acterics.racesclient.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by root on 30.10.17.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRaceRepository(apiService: ApiService, appDatabase: AppDatabase, raceMapper: RaceMapper): RaceRepository =
            RaceRepositoryImpl(apiService, appDatabase, raceMapper)


    @Provides
    @Singleton
    fun provideUserRepository(appDatabase: AppDatabase, apiService: ApiService, context: Context): UserRepository =
            UserRepositoryImpl(appDatabase, apiService, context)

    @Provides
    @Singleton
    fun provideHorseMapper(): HorseMapper = HorseMapper()

    @Provides
    @Singleton
    fun provideOrganizationMapper(): OrganizationMapper = OrganizationMapper()

    @Provides
    @Singleton
    fun provideParticipationMapper(horseMapper: HorseMapper): ParticipantMapper =
            ParticipantMapper(horseMapper)

    @Provides
    @Singleton
    fun provideRaceMapper(participantMapper: ParticipantMapper,
                          organizationMapper: OrganizationMapper): RaceMapper =
            RaceMapper(participantMapper, organizationMapper)

}