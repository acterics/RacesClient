package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Participant
import com.acterics.racesclient.data.database.entity.ParticipantEntity
import com.acterics.racesclient.data.database.entity.relation.ParticipantWithHorse
import com.acterics.racesclient.data.network.model.ParticipantModel
import java.util.Arrays.asList

/**
 * Created by root on 30.10.17.
 */
class ParticipantMapper(private val horseMapper: HorseMapper,
                        private val betMapper: BetMapper) {
    fun toDomain(from: ParticipantEntity): Participant =
            Participant(from.id, null, from.rating, asList())

    fun toDomain(from: ParticipantWithHorse): Participant =
            toDomain(from.participant).also {
                it.horse = horseMapper.toDomain(from.horse.first())
                it.bets = from.bets.map { betMapper.toDomain(it) }
            }

    fun toDomain(from: ParticipantModel): Participant =
            Participant(from.id,
                    horseMapper.toDomain(from.horse), from.rating,
                    from.bets?.map { betMapper.toDomain(it) })

//    fun toEntity(from: ParticipantModel): ParticipantEntity =
//            ParticipantEntity(from.id, from.rating, from.horse.id, from.raceId)
}