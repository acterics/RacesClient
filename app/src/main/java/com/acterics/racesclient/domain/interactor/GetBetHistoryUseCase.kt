package com.acterics.racesclient.domain.interactor

import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.model.dto.HistoryBet
import com.acterics.racesclient.domain.repository.UserRepository
import io.reactivex.Single

/**
 * Created by root on 03.11.17.
 */
class GetBetHistoryUseCase(private val userRepository: UserRepository,
                           private val scheduler: ExecutionScheduler):
        UseCase.AsSingle<List<HistoryBet>, GetBetHistoryUseCase.Param>() {

    override fun build(params: Param?): Single<List<HistoryBet>> =
        userRepository.getBetHistory(params!!.skip, params.count, false)
                .compose(scheduler.highPrioritySingle())


    data class Param(val skip: Int,
                     val count: Int)
}