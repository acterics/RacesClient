package com.acterics.racesclient.domain.interactor

import com.acterics.racesclient.data.AppDatabase
import com.acterics.racesclient.data.entity.Horse
import com.acterics.racesclient.data.entity.Participant
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import com.acterics.racesclient.common.extentions.checkNetworkSingle
import io.reactivex.Single
import io.reactivex.rxkotlin.zipWith
import javax.inject.Inject

/**
 * Created by root on 26.10.17.
 */
class GetRaceDetails
@Inject constructor(private val apiService: ApiService,
                    private val appDatabase: AppDatabase,
                    private val scheduler: ExecutionScheduler): UseCase.AsSingle<Race, GetRaceDetails.Params>() {



    override fun build(params: Params?): Single<Race> {
        return databaseRequest(params)
    }


    private fun databaseRequest(params: Params?): Single<Race> =
            appDatabase.horseDao().getRaceHorses(params!!.raceId)
                    .zipWith(appDatabase.participantDao().getRaceParticipants(params.raceId)
                            .compose(scheduler.highPrioritySingle()),
                            { horses, participants -> zipParticipantsWithHorses(horses, participants) })
                    .zipWith(appDatabase.raceDao().getRace(params.raceId)
                            .compose(scheduler.highPrioritySingle()),
                            { participants, race -> race.apply { this.participants = participants } })
                    .compose(scheduler.highPrioritySingle())



    private fun networkRequest(params: Params?): Single<Race> =
            apiService.getRace(params!!.raceId)
                    .checkNetworkSingle()
                    .map { it.map() }
                    .compose(scheduler.highPrioritySingle())

    private fun zipParticipantsWithHorses(horses: List<Horse>, participants: List<Participant>) =
            participants.zip(horses, {
                participant, horse -> participant.apply { this.horse = horse }
            })


    data class Params(val raceId: Long)
}