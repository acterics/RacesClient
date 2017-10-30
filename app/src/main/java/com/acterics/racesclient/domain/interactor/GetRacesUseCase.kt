package com.acterics.racesclient.domain.interactor

import com.acterics.racesclient.data.database.entity.RaceEntity
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.domain.model.Race
import com.acterics.racesclient.domain.repository.RaceRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by root on 24.10.17.
 */
class GetRacesUseCase
@Inject constructor(private val raceRepository: RaceRepository,
                    private val scheduler: ExecutionScheduler): UseCase.AsSingle<List<Race>, GetRacesUseCase.Params>() {



    private var loaded = false
    override fun build(params: Params?): Single<List<Race>> =
        raceRepository.getSchedulePage(params!!.skip, params.count, false )
                .compose(scheduler.highPrioritySingle())


    data class Params(val skip: Int,
                      val count: Int)
}