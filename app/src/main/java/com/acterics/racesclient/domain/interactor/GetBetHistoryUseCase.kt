package com.acterics.racesclient.domain.interactor

import com.acterics.domain.model.dto.HistoryBet
import com.acterics.domain.repository.UserRepository
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import io.reactivex.Single

/**
 * Created by root on 03.11.17.
 */
class GetBetHistoryUseCase(private val userRepository: UserRepository,
                           private val scheduler: ExecutionScheduler):
        UseCase.AsSingle<List<HistoryBet>, GetBetHistoryUseCase.Params>() {

    override fun build(params: Params?): Single<List<HistoryBet>> = userRepository
            .getBetHistory(params!!.skip, params.count, false)
            .compose(scheduler.highPrioritySingle())


    data class Params(val skip: Int,
                      val count: Int)
}