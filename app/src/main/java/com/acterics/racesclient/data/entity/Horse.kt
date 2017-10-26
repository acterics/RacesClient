package com.acterics.racesclient.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * Created by root on 18.10.17.
 */
@Entity
data class Horse(var id: Long = 0,
                 @ColumnInfo(name = "horse_name") var name: String,
                 @PrimaryKey(autoGenerate = true) var internalId: Long = 0)