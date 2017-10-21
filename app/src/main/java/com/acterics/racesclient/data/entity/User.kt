package com.acterics.racesclient.data.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by root on 09.10.17.
 */
data class User(val id: Long,
                val firstName: String,
                val lastName: String,
                val email: String,
                val avatar: String)