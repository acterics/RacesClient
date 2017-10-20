package com.acterics.racesclient.data.entity

import android.os.Parcel
import android.os.Parcelable
import org.joda.time.DateTime

/**
 * Created by root on 15.10.17.
 */

data class Race(val id: Long,
                val title: String,
                val organizer: Organization,
                val date: DateTime) : Parcelable {
    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readParcelable<Organization>(Organization::class.java.classLoader),
            source.readSerializable() as DateTime
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(title)
        writeParcelable(organizer, 0)
        writeSerializable(date)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Race> = object : Parcelable.Creator<Race> {
            override fun createFromParcel(source: Parcel): Race = Race(source)
            override fun newArray(size: Int): Array<Race?> = arrayOfNulls(size)
        }
    }
}