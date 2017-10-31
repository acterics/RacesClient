package com.acterics.racesclient.data.mapper

import com.acterics.racesclient.data.database.entity.BetEntity
import com.acterics.racesclient.data.network.model.BetModel
import com.acterics.racesclient.domain.model.Bet

/**
 * Created by root on 30.10.17.
 */
class BetMapper {
    fun toDomain(from: BetEntity): Bet = Bet(from.value, from.rate)

    fun toDomain(from: BetModel): Bet = Bet(from.bet, from.rating)

    fun toEntry(from: BetModel): BetEntity =
            BetEntity(from.id, from.bet, from.rating, from.participantId)
}