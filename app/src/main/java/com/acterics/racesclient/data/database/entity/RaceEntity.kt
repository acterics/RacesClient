package com.acterics.racesclient.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by root on 15.10.17.
 */
@Entity(
        foreignKeys = [
                (ForeignKey(
                        entity = OrganizationEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("race_organizer"),
                        onDelete = ForeignKey.CASCADE
                ))
        ],
        tableName = "race"
)
data class RaceEntity(
        @PrimaryKey
        @ColumnInfo(name = "id") var id: Long = 0,
        @ColumnInfo(name = "race_title") var title: String = "",
        @ColumnInfo(name = "race_date") var date: Long = 0,
        @ColumnInfo(name = "race_organizer") var organizerId: Long = 0
)

