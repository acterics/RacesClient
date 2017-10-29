package com.acterics.racesclient.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * Created by root on 18.10.17.
 */
@Entity(tableName = "horse")
data class Horse(@PrimaryKey
                 @ColumnInfo(name = "id") var id: Long = 0,
                 @ColumnInfo(name = "horse_name") var name: String = "")