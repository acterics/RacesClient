package com.acterics.racesclient.di.modules

import com.acterics.racesclient.ApplicationConfiguration
import com.acterics.racesclient.ReleaseApplicationConfiguration
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by root on 19.10.17.
 */
@Module
class BuildModule {
    @Provides
    @Singleton
    fun provideApplicationConfiguration(): ApplicationConfiguration {
        return ReleaseApplicationConfiguration()
    }
}