package com.acterics.racesclient.di.auth

import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.interactor.SignInUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by root on 29.10.17.
 */

@Module
class AuthenticationModule {

    @AuthenticationScope
    @Provides
    fun provideAuthenticateUseCase(apiService: ApiService, scheduler: ExecutionScheduler): SignInUseCase =
            SignInUseCase(apiService, scheduler)

}