package com.acterics.racesclient.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.acterics.racesclient.data.AppDatabase
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.executor.ThreadScheduler
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
    fun provideContext(): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideExecutionScheduler(threadScheduler: ThreadScheduler): ExecutionScheduler = threadScheduler


    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "races_database")
                .build()
    }





}