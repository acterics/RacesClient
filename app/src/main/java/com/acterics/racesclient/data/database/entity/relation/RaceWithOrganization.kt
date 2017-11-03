package com.acterics.racesclient.data.database.entity.relation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.acterics.racesclient.data.database.entity.OrganizationEntity
import com.acterics.racesclient.data.database.entity.RaceEntity

/**
 * Created by root on 30.10.17.
 */
class RaceWithOrganization {
        @Embedded
        lateinit var race: RaceEntity

        @Relation(parentColumn = "race_organizer", entityColumn = "id", entity = OrganizationEntity::class)
        lateinit var organization: List<OrganizationEntity>
}