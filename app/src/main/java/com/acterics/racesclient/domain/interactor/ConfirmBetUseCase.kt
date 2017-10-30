package com.acterics.racesclient.domain.interactor

import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.repository.UserRepository
import io.reactivex.Single

/**
 * Created by root on 30.10.17.
 */
class ConfirmBetUseCase(private val userRepository: UserRepository,
                        private val scheduler: ExecutionScheduler): UseCase.AsSingle<Boolean, ConfirmBetUseCase.Params>() {
    override fun build(params: Params?): Single<Boolean> =
        userRepository.addBet(params!!.bet, params.rating, params.participantId, true)
                .compose(scheduler.highPrioritySingle())


    data class Params(val bet: Float,
                      val rating: Float,
                      val participantId: Long)
}