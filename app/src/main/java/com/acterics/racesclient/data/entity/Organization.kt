package com.acterics.racesclient.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by root on 15.10.17.
 */
@Entity(tableName = "organization")
data class Organization(@PrimaryKey
                        @ColumnInfo(name = "id") var id: Long = 0,
                        @ColumnInfo(name = "organization_name") var name: String = ""
)
