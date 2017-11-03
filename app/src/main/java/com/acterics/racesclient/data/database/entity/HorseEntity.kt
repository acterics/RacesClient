package com.acterics.racesclient.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.acterics.racesclient.domain.model.Horse


/**
 * Created by root on 18.10.17.
 */
@Entity(tableName = "horse")
data class HorseEntity(@PrimaryKey
                       @ColumnInfo(name = "id") var id: Long = 0,
                       @ColumnInfo(name = "horse_name") var name: String = ""
)