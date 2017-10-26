package com.acterics.racesclient.data.usecase

import com.acterics.racesclient.data.AppDatabase
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.domain.UseCase
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.utils.checkNetworkSingle
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by root on 26.10.17.
 */
class GetRaceDetails
@Inject constructor(private val apiService: ApiService,
                    private val appDatabase: AppDatabase,
                    private val scheduler: ExecutionScheduler): UseCase.AsSingle<Race, GetRaceDetails.Params>() {



    override fun build(params: Params?): Single<Race> =
            apiService.getRace(params!!.raceId)
                    .checkNetworkSingle()
                    .map { it.map() }
                    .compose(scheduler.highPrioritySingle())

    data class Params(val raceId: Long)
}