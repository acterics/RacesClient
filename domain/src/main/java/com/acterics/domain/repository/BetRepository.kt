package com.acterics.domain.repository

import com.acterics.domain.model.Bet
import com.acterics.domain.model.Participant
import com.acterics.domain.model.dto.HistoryBet
import com.acterics.domain.model.dto.Page
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by oleg on 22.01.18.
 */
interface BetRepository {
    fun addBet(bet: Bet, participant: Participant): Completable
    fun getBetHistory(page: Page, caching: Boolean): Single<List<HistoryBet>>
}