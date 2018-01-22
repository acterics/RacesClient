package com.acterics.racesclient.di.auth

import com.acterics.domain.interactor.AuthenticateInteractor
import com.acterics.domain.repository.UserRepository
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.interactor.AuthenticateInteractorImpl
import dagger.Module
import dagger.Provides

/**
 * Created by root on 29.10.17.
 */

@Module
class AuthenticationModule {

    @AuthenticationScope
    @Provides
    fun provideAuthenticateInteractor(userRepository: UserRepository,
                                      scheduler: ExecutionScheduler): AuthenticateInteractor {
        return AuthenticateInteractorImpl(userRepository, scheduler)
    }

}