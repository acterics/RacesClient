package com.acterics.racesclient.data.repository

import com.acterics.domain.model.Race
import com.acterics.domain.model.dto.Page
import com.acterics.domain.repository.RaceRepository
import com.acterics.racesclient.common.extentions.checkNetworkSingle
import com.acterics.racesclient.common.extentions.listMap
import com.acterics.racesclient.data.database.AppDatabase
import com.acterics.racesclient.data.mapper.RaceMapper
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.model.RaceModel
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle

/**
 * Created by root on 29.10.17.
 */
class RaceRepositoryImpl(private val apiService: ApiService,
                         private val appDatabase: AppDatabase,
                         private val raceMapper: RaceMapper): RaceRepository {


    override fun getSchedulePage(page: Page, caching: Boolean): Single<List<Race>> =
        apiService.getRaces(page.skip, page.count)
                .checkNetworkSingle()
                .map { it.races }
                .flatMap { if (caching) cacheSchedulePage(it) else Single.just(it) }
                .listMap { raceMapper.toDomain(it) }


    override fun getRaceDetails(raceId: Long, fromCache: Boolean): Single<Race> =
       fromCache.toSingle()
               .flatMap {
                   if (it) raceDatabaseRequest(raceId)
                   else raceNetworkRequest(raceId)
               }


    private fun cacheSchedulePage(races: List<RaceModel>): Single<List<RaceModel>> {
        return Single.just(races)
    }

    private fun raceDatabaseRequest(raceId: Long): Single<Race>  {
        return appDatabase.raceDao()
                .getRace(raceId)
                .map { raceMapper.toDomain(it) }
    }

    private fun raceNetworkRequest(raceId: Long): Single<Race> {
        return apiService.getRace(raceId)
                .checkNetworkSingle()
                .map { raceMapper.toDomain(it) }
    }

    //    override fun getSchedulePage(skip: Int, count: Int, caching: Boolean): Single<List<Race>> =
//        apiService.getSchedule(skip, count)
//                .checkNetworkSingle()
//                .map { it.races.map { it.map() } }
//                .flatMap { if (caching) cacheRaces(it) else Single.just(it) }


//    override fun getRaceDetails(raceId: Long, fromCache: Boolean): Single<Race> =
//        if (fromCache) databaseRaceDetails(raceId) else networkRaceDetails(raceId)



//    private fun databaseRaceDetails(raceId: Long): Single<Race> =
//            appDatabase.horseDao().getRaceHorses(raceId)
//                    .zipWith(appDatabase.participantDao().getRaceParticipants(raceId),
//                            { horses, participants -> zipParticipantsWithHorses(horses, participants) })
//                    .zipWith(appDatabase.raceDao().getRace(raceId),
//                            { participants, race -> race.apply { this.participants = participants } })




//    private fun networkRaceDetails(raceId: Long): Single<Race> =
//            apiService.getRace(raceId)
//                    .checkNetworkSingle()
//                    .map { it.map() }

//    private fun zipParticipantsWithHorses(horses: List<HorseEntity>, participants: List<ParticipantEntity>) =
//            participants.zip(horses, {
//                participant, horse -> participant.apply { this.horse = horse }
//            })


//    private fun cacheRaces(races: List<Race>): Single<List<Race>> {
//        return appDatabase.apply {
//            beginTransaction()
//            races
//                    .also { organizationDao().insertAll(it.map { it.organizer ?: throw IllegalStateException() }) }
//                    .also { raceDao().insertAll(it) }
//                    .flatMap { it.participants ?: throw IllegalStateException() }
//                    .also { horseDao().insertAll(
//                            it
//                                    .map { it.horse ?: throw IllegalStateException() }
//                                    .groupBy { it.id }.values
//                                    .map { it.first() }) }
//                    .also { participantDao().insertAll(it) }
//            setTransactionSuccessful()
//        }
//                .let { races }
//                .toSingle()
//                .doOnEvent { _, _ -> appDatabase.endTransaction() }
//    }
}