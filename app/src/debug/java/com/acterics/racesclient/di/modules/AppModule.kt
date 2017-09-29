package com.acterics.racesclient.di.modules

import android.app.Application
import android.content.Context
import com.acterics.racesclient.DebugApplicationConfiguration
import com.acterics.racesclient.ApplicationConfiguration
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by root on 28.09.17.
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
    fun provideApplicationConfiguration(): ApplicationConfiguration {
        return DebugApplicationConfiguration()
    }


}