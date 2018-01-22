package com.acterics.racesclient.di.profile

import com.acterics.domain.interactor.ProfileInteractor
import com.acterics.domain.repository.BetRepository
import com.acterics.domain.repository.UserRepository
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.interactor.ProfileInteractorImpl
import dagger.Module
import dagger.Provides

/**
 * Created by root on 03.11.17.
 */
@Module
class ProfileModule {


    @ProfileScope
    @Provides
    fun provideProfileInteractor(userRepository: UserRepository,
                                 betRepository: BetRepository,
                                 scheduler: ExecutionScheduler): ProfileInteractor {
        return ProfileInteractorImpl(userRepository, betRepository, scheduler)
    }
}