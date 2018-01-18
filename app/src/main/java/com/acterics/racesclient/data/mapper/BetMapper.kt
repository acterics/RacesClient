package com.acterics.racesclient.data.mapper

import com.acterics.racesclient.data.database.entity.BetEntity
import com.acterics.racesclient.data.network.model.BetModel
import com.acterics.racesclient.data.network.model.HistoryBetModel
import com.acterics.racesclient.data.network.model.request.BetRequest
import com.acterics.domain.model.Bet
import com.acterics.domain.model.dto.HistoryBet
import org.joda.time.DateTime

/**
 * Created by root on 30.10.17.
 */
class BetMapper {
    fun toDomain(from: BetEntity): Bet = Bet(from.value, from.rate)

    fun toDomain(from: BetModel): Bet = Bet(from.bet, from.rating)

    fun toDomain(from: BetRequest): Bet = Bet(from.bet, from.rating)

    fun toEntity(from: BetModel): BetEntity =
            BetEntity(from.id, from.bet, from.rating, from.participantId)


    //TODO move to another mapper
    fun toDto(from: HistoryBetModel): HistoryBet =
            HistoryBet(Bet(from.bet, from.rating), DateTime(from.raceDate * 1000), from.success, from.result, from.horseName)
}