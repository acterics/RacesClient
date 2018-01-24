package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Bet
import com.acterics.domain.model.Participant
import com.acterics.domain.model.dto.HistoryBet
import com.acterics.racesclient.data.database.entity.BetEntity
import com.acterics.racesclient.data.network.model.BetModel
import com.acterics.racesclient.data.network.model.HistoryBetModel
import com.acterics.racesclient.data.network.model.request.BetRequest
import java.util.*
import javax.inject.Inject

/**
 * Created by root on 30.10.17.
 */
class BetMapper
@Inject constructor(){
    fun toDomain(from: BetEntity): Bet = Bet(from.value, from.rate)

    fun toDomain(from: BetModel): Bet = Bet(from.bet, from.rating)

    fun toDomain(from: BetRequest): Bet = Bet(from.bet, from.rating)

    fun toEntity(from: BetModel): BetEntity =
            BetEntity(from.id, from.bet, from.rating, from.participantId)

    fun toRequest(from: Bet, participant: Participant) =
            BetRequest(participant.id, from.bet, from.rating)

    //TODO move to another mapper
    fun toDto(from: HistoryBetModel): HistoryBet =
            HistoryBet(Bet(from.bet, from.rating), Date(from.raceDate * 1000), from.success, from.result, from.horseName)
}