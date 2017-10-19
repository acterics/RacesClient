package com.acterics.racesclient.di.modules

import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.utils.DebugApiService
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