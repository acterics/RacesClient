package com.acterics.racesclient.domain.interactor

import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.model.Race
import com.acterics.racesclient.domain.repository.RaceRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by root on 26.10.17.
 */
//TODO Add memory caching
class GetRaceDetailsUseCase
@Inject constructor(private val raceRepository: RaceRepository,
                    private val scheduler: ExecutionScheduler): UseCase.AsSingle<Race, GetRaceDetailsUseCase.Params>() {



    override fun build(params: Params?): Single<Race> {
        return raceRepository.getRaceDetails(params!!.raceId, false)
                .compose(scheduler.highPrioritySingle())
    }


    data class Params(val raceId: Long)
}