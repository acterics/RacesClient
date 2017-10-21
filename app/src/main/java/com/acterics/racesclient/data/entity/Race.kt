package com.acterics.racesclient.data.entity

import android.os.Parcel
import android.os.Parcelable
import org.joda.time.DateTime

/**
 * Created by root on 15.10.17.
 */

data class Race(val id: Long,
                val title: String,
                val organizerId: Long,
                val date: DateTime)