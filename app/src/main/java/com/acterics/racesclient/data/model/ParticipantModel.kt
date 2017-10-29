package com.acterics.racesclient.data.model

import com.acterics.racesclient.data.entity.Participant
import org.joda.money.Money

/**
 * Created by root on 21.10.17.
 */
data class ParticipantModel(val id: Long,
                            val raceId: Long,
                            val horse: HorseModel,
                            val rating: Float,
                            val currentBet: Money?): EntityWrapper<Participant> {
    override fun map(): Participant = Participant(id, rating, horse.id, raceId, horse = horse.map())

}