package com.acterics.racesclient.data.entity

import android.arch.persistence.room.*


/**
 * Created by root on 19.10.17.
 */
@Entity(foreignKeys = arrayOf(
            ForeignKey(
                    entity = Race::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("race_id"),
                    onDelete = ForeignKey.CASCADE),
            ForeignKey(
                    entity = Horse::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("horse_id"),
                    onDelete = ForeignKey.CASCADE
            )),
        tableName = "participant")
data class Participant (
        @PrimaryKey
        @ColumnInfo(name = "participant_id") var id: Long = 0,
        @ColumnInfo(name = "rating") var rating: Float = 0.0f,
        @ColumnInfo(name = "horse_id") var horseId: Long = 0,
        @ColumnInfo(name = "race_id") var raceId: Long = 0,
        @Ignore var horse: Horse? = null,
        @Ignore var race: Race? = null
)

