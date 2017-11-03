package com.acterics.racesclient.data.database.entity.relation

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.acterics.racesclient.data.database.entity.BetEntity
import com.acterics.racesclient.data.database.entity.HorseEntity
import com.acterics.racesclient.data.database.entity.ParticipantEntity

/**
 * Created by root on 30.10.17.
 */
class ParticipantWithHorse {
        @Embedded
        lateinit var participant: ParticipantEntity

        @Relation(parentColumn = "horse_id", entityColumn = "id", entity = HorseEntity::class)
        lateinit var horse: List<HorseEntity>

        @Relation(parentColumn = "participant_id", entityColumn = "participant_id")
        lateinit var bets: List<BetEntity>
}