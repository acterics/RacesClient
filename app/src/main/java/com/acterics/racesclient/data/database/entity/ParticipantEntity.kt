package com.acterics.racesclient.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


/**
 * Created by root on 19.10.17.
 */
@Entity(foreignKeys = arrayOf(
            ForeignKey(
                    entity = RaceEntity::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("race_id"),
                    onDelete = ForeignKey.CASCADE),
            ForeignKey(
                    entity = HorseEntity::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("horse_id"),
                    onDelete = ForeignKey.CASCADE)
),
        tableName = "participant")
data class ParticipantEntity(
        @PrimaryKey
        @ColumnInfo(name = "participant_id") var id: Long = 0,
        @ColumnInfo(name = "rating") var rating: Float = 0.0f,
        @ColumnInfo(name = "horse_id") var horseId: Long = 0,
        @ColumnInfo(name = "race_id") var raceId: Long = 0
)

