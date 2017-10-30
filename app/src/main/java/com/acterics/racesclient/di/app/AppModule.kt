package com.acterics.racesclient.di.app

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.acterics.racesclient.data.database.AppDatabase
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.repository.RaceRepositoryImpl
import com.acterics.racesclient.data.repository.UserRepositoryImpl
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.executor.ThreadScheduler
import com.acterics.racesclient.domain.repository.RaceRepository
import com.acterics.racesclient.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by root on 19.10.17.
 */
@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context  = app.applicationContext


    @Provides
    @Singleton
    fun provideExecutionScheduler(threadScheduler: ThreadScheduler): ExecutionScheduler = threadScheduler


    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "races_database").build()

    @Provides
    @Singleton
    fun provideRaceRepository(apiService: ApiService, appDatabase: AppDatabase): RaceRepository =
            RaceRepositoryImpl(apiService, appDatabase)


    @Provides
    @Singleton
    fun provideUserRepository(appDatabase: AppDatabase, apiService: ApiService, context: Context): UserRepository =
            UserRepositoryImpl(appDatabase, apiService, context)


}