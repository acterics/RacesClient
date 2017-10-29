package com.acterics.racesclient.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by root on 09.10.17.
 */
@Entity
data class User(var id: Long = 0,
                @ColumnInfo(name = "first_name") var firstName: String,
                @ColumnInfo(name = "last_name") var lastName: String,
                @ColumnInfo(name = "email") var email: String,
                @ColumnInfo(name = "avatar") var avatar: String,
                @PrimaryKey(autoGenerate = true) var internalId: Long = 0)