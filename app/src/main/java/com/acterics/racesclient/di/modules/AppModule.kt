package com.acterics.racesclient.di.modules

import android.app.Application
import android.content.Context
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

}