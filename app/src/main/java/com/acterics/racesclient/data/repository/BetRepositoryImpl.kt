package com.acterics.racesclient.data.repository

import com.acterics.domain.model.Bet
import com.acterics.domain.model.Participant
import com.acterics.domain.model.dto.HistoryBet
import com.acterics.domain.model.dto.Page
import com.acterics.domain.repository.BetRepository
import com.acterics.racesclient.common.extentions.checkNetworkSingle
import com.acterics.racesclient.common.extentions.listMap
import com.acterics.racesclient.data.mapper.BetMapper
import com.acterics.racesclient.data.network.ApiService
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by oleg on 22.01.18.
 */
class BetRepositoryImpl
@Inject constructor(private val apiService: ApiService,
                    private val betMapper: BetMapper): BetRepository {

    override fun addBet(bet: Bet, participant: Participant): Completable =
        Single.just(betMapper.toRequest(bet, participant))
                .flatMap { apiService.addBet(it) }
                .checkNetworkSingle()
                .toCompletable()


    override fun getBetHistory(page: Page, caching: Boolean): Single<List<HistoryBet>> {
        return apiService.getBets(page.skip, page.count)
                .checkNetworkSingle()
                .map { it.bets }
                .listMap { betMapper.toDto(it) }
    }

}