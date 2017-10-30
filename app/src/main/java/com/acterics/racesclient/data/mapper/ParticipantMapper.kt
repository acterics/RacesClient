package com.acterics.racesclient.data.mapper

import com.acterics.racesclient.data.database.entity.ParticipantEntity
import com.acterics.racesclient.data.database.entity.relation.ParticipantWithHorse
import com.acterics.racesclient.data.network.model.ParticipantModel
import com.acterics.racesclient.domain.model.Participant

/**
 * Created by root on 30.10.17.
 */
class ParticipantMapper(private val horseMapper: HorseMapper) {
    fun toDomain(from: ParticipantEntity): Participant =
            Participant(from.id, null, from.rating)

    fun toDomain(from: ParticipantWithHorse): Participant =
            toDomain(from.participant).also {
                it.horse = horseMapper.toDomain(from.horse.first())
            }

    fun toDomain(from: ParticipantModel): Participant =
            Participant(from.id, horseMapper.toDomain(from.horse), from.rating)

    fun toEntity(from: ParticipantModel): ParticipantEntity =
            ParticipantEntity(from.id, from.rating, from.horse.id, from.raceId)
}