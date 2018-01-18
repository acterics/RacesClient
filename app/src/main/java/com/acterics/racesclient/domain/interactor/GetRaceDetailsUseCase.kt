package com.acterics.racesclient.domain.interactor

import com.acterics.domain.model.Race
import com.acterics.domain.repository.RaceRepository
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by root on 26.10.17.
 */
//TODO Add memory caching
class GetRaceDetailsUseCase
@Inject constructor(private val raceRepository: RaceRepository,
                    private val scheduler: ExecutionScheduler): UseCase.AsSingle<Race, GetRaceDetailsUseCase.Params>() {


    override fun build(params: Params?): Single<Race> = raceRepository
            .getRaceDetails(params!!.raceId, false)
            .compose(scheduler.highPrioritySingle())

    data class Params(val raceId: Long)
}