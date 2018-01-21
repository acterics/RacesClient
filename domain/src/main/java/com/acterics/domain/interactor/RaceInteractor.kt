package com.acterics.domain.interactor

import com.acterics.domain.model.Bet
import com.acterics.domain.model.Participant
import com.acterics.domain.model.Race
import com.acterics.domain.model.dto.Page
import io.reactivex.Single

/**
 * Created by oleg on 21.01.18.
 */
interface RaceInteractor: Interactor {

    fun getRaceList(page: Page): Single<List<Race>>
    fun getRaceDetails(raceId: Long): Single<Race>
    fun addBet(bet: Bet, participant: Participant)


}