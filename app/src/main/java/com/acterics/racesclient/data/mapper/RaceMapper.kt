package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Race
import com.acterics.racesclient.data.database.entity.RaceEntity
import com.acterics.racesclient.data.database.entity.relation.RaceWithOrganization
import com.acterics.racesclient.data.database.entity.relation.RaceWithParticipants
import com.acterics.racesclient.data.network.model.RaceModel
import org.joda.time.DateTime
import javax.inject.Inject

/**
 * Created by root on 30.10.17.
 */
class RaceMapper
@Inject constructor(private val participantMapper: ParticipantMapper,
                    private val organizationMapper: OrganizationMapper) {
    fun toDomain(from: RaceEntity): Race =
            Race(from.id, from.title, DateTime(from.date * 1000), null, listOf())

    fun toDomain(from: RaceWithOrganization): Race =
            toDomain(from.race).also {
                it.organizer = organizationMapper.toDomain(from.organization.first())
            }

    fun toDomain(from: RaceWithParticipants): Race =
            toDomain(from.race).also {
                it.organizer = organizationMapper.toDomain(from.organization.first())
                it.participants = from.participants.map { participantMapper.toDomain(it) }
            }
    fun toDomain(from: RaceModel): Race =
            Race(from.id, from.title, DateTime(from.dateTime * 1000),
                    organizationMapper.toDomain(from.organizer),
                    from.participants.map { participantMapper.toDomain(it) })

    fun toEntity(from: RaceModel): RaceEntity =
            RaceEntity(from.id, from.title, from.dateTime, from.organizer.id)

}