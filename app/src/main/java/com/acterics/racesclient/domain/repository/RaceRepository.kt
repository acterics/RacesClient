package com.acterics.racesclient.domain.repository


import com.acterics.racesclient.domain.model.Race
import io.reactivex.Single

/**
 * Created by root on 29.10.17.
 */
interface RaceRepository {

    fun getSchedulePage(skip: Int, count: Int, caching: Boolean): Single<List<Race>>
    fun getRaceDetails(raceId: Long, fromCache: Boolean): Single<Race>

}