package com.acterics.racesclient.di.app

import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.DebugApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by root on 19.10.17.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return DebugApiService()
    }
}