package com.acterics.racesclient.di.modules

import com.acterics.racesclient.DebugApplicationConfiguration
import com.acterics.racesclient.ApplicationConfiguration
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