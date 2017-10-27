package com.acterics.racesclient.data.entity

import android.arch.persistence.room.*
import org.joda.time.DateTime

/**
 * Created by root on 15.10.17.
 */
@Entity(foreignKeys = arrayOf(
                ForeignKey(
                        entity = Organization::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("race_organizer"),
                        onDelete = ForeignKey.CASCADE)),
        tableName = "race")

data class Race (
        @PrimaryKey
        @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "race_title") var title: String = "",
        @ColumnInfo(name = "race_date") var date: Long = 0,
        @ColumnInfo(name = "race_organizer") var organizerId: Long = 0,


        @Ignore var organizer: Organization? = null,
        @Ignore var dateTime: DateTime? = null,
        @Ignore var participants: List<Participant>? = null
)