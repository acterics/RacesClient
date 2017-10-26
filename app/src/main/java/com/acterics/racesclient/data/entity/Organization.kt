package com.acterics.racesclient.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
/**
 * Created by root on 15.10.17.
 */
@Entity
data class Organization(var id: Long = 0,
                        @ColumnInfo(name = "organization_name") var name: String,
                        @PrimaryKey(autoGenerate = true) var internalId: Long = 0)
