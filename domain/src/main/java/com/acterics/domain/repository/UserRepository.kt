package com.acterics.domain.repository

import com.acterics.domain.model.Bet
import com.acterics.domain.model.dto.HistoryBet
import io.reactivex.Single

/**
 * Created by root on 30.10.17.
 */
interface UserRepository {

    fun addBet(bet: Float, rating: Float,  participationId: Long, caching: Boolean):
            Single<Bet>
    fun getBetHistory(skip: Int, count: Int, caching: Boolean):
            Single<List<HistoryBet>>


//    fun getUser(): Single<User>
    fun saveUser(): Single<Boolean>
}