package com.acterics.racesclient.di.profile

import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.interactor.GetBetHistoryUseCase
import com.acterics.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

/**
 * Created by root on 03.11.17.
 */
@Module
class ProfileModule {

    @Provides
    @ProfileScope
    fun provideGetBetHistoryUseCase(userRepository: UserRepository,
                                    scheduler: ExecutionScheduler): GetBetHistoryUseCase =
            GetBetHistoryUseCase(userRepository, scheduler)


}