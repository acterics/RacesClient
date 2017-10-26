package com.acterics.racesclient.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime

/**
 * Created by root on 15.10.17.
 */
@Entity(tableName = "race")
data class Race (
        var id: Long = 0,
        @ColumnInfo(name = "race_title") var title: String = "",
        @ColumnInfo(name = "race_date") var date: Long = 0,
        @ColumnInfo(name = "race_organizer") var organizerId: Long = 0,
        @PrimaryKey(autoGenerate = true) var internalId: Long = 0,
        @Ignore var organizer: Organization? = null,
        @Ignore var dateTime: DateTime? = null,
        @Ignore var participants: List<Participant>? = null
)