package com.acterics.racesclient.di.app

import com.acterics.racesclient.ApplicationConfiguration
import com.acterics.racesclient.DebugApplicationConfiguration
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by root on 28.09.17.
 */
@Module
class BuildModule {

    @Provides
    @Singleton
    fun provideApplicationConfiguration(): ApplicationConfiguration {
        return DebugApplicationConfiguration()
    }
}