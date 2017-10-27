package com.acterics.racesclient.data.usecase

import com.acterics.racesclient.data.AppDatabase
import com.acterics.racesclient.domain.UseCase
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.utils.checkNetworkSingle
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by root on 24.10.17.
 */
class GetRaces
@Inject constructor(private val apiService: ApiService,
                    private val appDatabase: AppDatabase,
                    private val scheduler: ExecutionScheduler): UseCase.AsSingle<List<Race>, GetRaces.Params>() {



    private var loaded = false
    override fun build(params: Params?): Single<List<Race>> =
        apiService.getSchedule(params!!.skip, params.count)
                .checkNetworkSingle()
                .map { it.races.map { it.map() } }
                .flatMap { cacheResponse(it) }
                .doOnSuccess { loaded = true }
                .compose(scheduler.highPrioritySingle())


    private fun cacheResponse(races: List<Race>): Single<List<Race>> {
        return Single.fromCallable {
            appDatabase.apply {
                beginTransaction()
                races
                        .also { organizationDao().insertAll(it.map { it.organizer ?: throw IllegalStateException() }) }
                        .also { raceDao().insertAll(it) }
                        .flatMap { it.participants ?: throw IllegalStateException() }
                        .also { horseDao().insertAll(it.map { it.horse ?: throw IllegalStateException() }) }
                        .also { participantDao().insertAll(it) }
                setTransactionSuccessful()
            }.let { races }
        }
                .doOnEvent { _, _ -> appDatabase.endTransaction() }
                .compose(scheduler.highPrioritySingle())
    }

    data class Params(val skip: Int,
                      val count: Int)
}