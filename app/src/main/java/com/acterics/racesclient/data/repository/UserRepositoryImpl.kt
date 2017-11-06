package com.acterics.racesclient.data.repository

import android.content.Context
import com.acterics.racesclient.common.extentions.checkNetworkSingle
import com.acterics.racesclient.common.extentions.getUser
import com.acterics.racesclient.common.extentions.listMap
import com.acterics.racesclient.data.database.AppDatabase
import com.acterics.racesclient.data.database.entity.User
import com.acterics.racesclient.data.mapper.BetMapper
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.model.BetModel
import com.acterics.racesclient.data.network.model.request.BetRequest
import com.acterics.racesclient.domain.model.Bet
import com.acterics.racesclient.domain.model.dto.HistoryBet
import com.acterics.racesclient.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle

/**
 * Created by root on 30.10.17.
 */
class UserRepositoryImpl(private val appDatabase: AppDatabase,
                         private val apiService: ApiService,
                         private val context: Context,
                         private val betMapper: BetMapper): UserRepository {


    override fun addBet(bet: Float, rating: Float, participationId: Long, caching: Boolean): Single<Bet> {
        return apiService.addBet(BetRequest(participationId, bet, rating))
                .checkNetworkSingle()
                .flatMap {
                    if(caching) cacheBetRequest(it)
                    else Single.just(betMapper.toDomain(it))
                }
    }

    override fun getUser(): Single<User> {
        return Single.just(context.getUser())
    }

    override fun saveUser(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBetHistory(skip: Int, count: Int, caching: Boolean): Single<List<HistoryBet>> {
        return apiService.getBets(skip, count)
                .checkNetworkSingle()
                .map { it.bets }
                .listMap { betMapper.toDto(it) }

    }

    private fun cacheBetRequest(betModel: BetModel): Single<Bet> =
        betModel.toSingle()
                .doOnSuccess { appDatabase.betDao().insert(betMapper.toEntry(betModel)) }
                .map { betMapper.toDomain(it) }

}