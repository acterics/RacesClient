package com.acterics.racesclient.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by root on 30.10.17.
 */
@Entity(
        foreignKeys = [
                (ForeignKey(
                        entity = ParticipantEntity::class,
                        parentColumns = arrayOf("participant_id"),
                        childColumns = arrayOf("participant_id"),
                        onDelete = ForeignKey.CASCADE
                ))
        ],
        tableName = "bet"
)
data class BetEntity(
        @PrimaryKey
        @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "value") var value: Float = 0.0f,
        @ColumnInfo(name = "rate") var rate: Float = 0.0f,
        @ColumnInfo(name = "participant_id") var participantId: Long = 0
)