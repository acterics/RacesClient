package com.acterics.racesclient.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey


/**
 * Created by root on 19.10.17.
 */
@Entity
data class Participant (
        var id: Long = 0,
        @ColumnInfo(name = "rating") var rating: Float = 0.0f,
        @ColumnInfo(name = "horse_id") var horseId: Long = 0,
        @ColumnInfo(name = "race_id") var raceId: Long = 0,
        @PrimaryKey(autoGenerate = true) var internalId: Long = 0,
        @Ignore var horse: Horse? = null,
        @Ignore var race: Race? = null
)

