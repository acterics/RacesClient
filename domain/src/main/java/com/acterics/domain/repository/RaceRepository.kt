package com.acterics.domain.repository


import com.acterics.domain.model.Race
import com.acterics.domain.model.dto.Page
import io.reactivex.Single

/**
 * Created by root on 29.10.17.
 */
interface RaceRepository {

    fun getSchedulePage(page: Page, caching: Boolean): Single<List<Race>>
    fun getRaceDetails(raceId: Long, fromCache: Boolean): Single<Race>

}