package com.acterics.racesclient.di.auth

import android.content.Context
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.interactor.SignInUseCase
import com.acterics.racesclient.domain.interactor.SignUpUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by root on 29.10.17.
 */

@Module
class AuthenticationModule {

    @AuthenticationScope
    @Provides
    fun provideSignInUseCase(apiService: ApiService,
                             scheduler: ExecutionScheduler,
                             context: Context): SignInUseCase =
            SignInUseCase(apiService, scheduler, context)

    @AuthenticationScope
    @Provides
    fun provideSignUpUseCase(apiService: ApiService,
                             scheduler: ExecutionScheduler,
                             context: Context): SignUpUseCase =
            SignUpUseCase(apiService, scheduler, context)

}