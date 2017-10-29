package com.acterics.racesclient.data.repository

import com.acterics.racesclient.common.extentions.checkNetworkSingle
import com.acterics.racesclient.data.database.AppDatabase
import com.acterics.racesclient.data.database.entity.Horse
import com.acterics.racesclient.data.database.entity.Participant
import com.acterics.racesclient.data.database.entity.Race
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.domain.repository.RaceRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import io.reactivex.rxkotlin.zipWith

/**
 * Created by root on 29.10.17.
 */
class RaceRepositoryImpl(private val apiService: ApiService,
                         private val appDatabase: AppDatabase): RaceRepository {

    override fun getSchedulePage(skip: Int, count: Int, caching: Boolean): Single<List<Race>> =
        apiService.getSchedule(skip, count)
                .checkNetworkSingle()
                .map { it.races.map { it.map() } }
                .flatMap { if (caching) cacheRaces(it) else Single.just(it) }


    override fun getRaceDetails(raceId: Long, fromCache: Boolean): Single<Race> =
        if (fromCache) databaseRaceDetails(raceId) else networkRaceDetails(raceId)



    private fun databaseRaceDetails(raceId: Long): Single<Race> =
            appDatabase.horseDao().getRaceHorses(raceId)
                    .zipWith(appDatabase.participantDao().getRaceParticipants(raceId),
                            { horses, participants -> zipParticipantsWithHorses(horses, participants) })
                    .zipWith(appDatabase.raceDao().getRace(raceId),
                            { participants, race -> race.apply { this.participants = participants } })




    private fun networkRaceDetails(raceId: Long): Single<Race> =
            apiService.getRace(raceId)
                    .checkNetworkSingle()
                    .map { it.map() }

    private fun zipParticipantsWithHorses(horses: List<Horse>, participants: List<Participant>) =
            participants.zip(horses, {
                participant, horse -> participant.apply { this.horse = horse }
            })


    private fun cacheRaces(races: List<Race>): Single<List<Race>> {
        return appDatabase.apply {
            beginTransaction()
            races
                    .also { organizationDao().insertAll(it.map { it.organizer ?: throw IllegalStateException() }) }
                    .also { raceDao().insertAll(it) }
                    .flatMap { it.participants ?: throw IllegalStateException() }
                    .also { horseDao().insertAll(
                            it
                                    .map { it.horse ?: throw IllegalStateException() }
                                    .groupBy { it.id }.values
                                    .map { it.first() }) }
                    .also { participantDao().insertAll(it) }
            setTransactionSuccessful()
        }
                .let { races }
                .toSingle()
                .doOnEvent { _, _ -> appDatabase.endTransaction() }
    }
}