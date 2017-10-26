package com.acterics.racesclient.data.translation

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by root on 24.10.17.
 */
data class ScheduleRaceTranslation(
        var raceId: Long,
        var raceTitle: String,
        var organizationTitle: String,
        var holderTranslationName: String,
        var titleTranslationName: String,
        var organizerTranslationName: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(raceId)
        writeString(raceTitle)
        writeString(organizationTitle)
        writeString(holderTranslationName)
        writeString(titleTranslationName)
        writeString(organizerTranslationName)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ScheduleRaceTranslation> = object : Parcelable.Creator<ScheduleRaceTranslation> {
            override fun createFromParcel(source: Parcel): ScheduleRaceTranslation = ScheduleRaceTranslation(source)
            override fun newArray(size: Int): Array<ScheduleRaceTranslation?> = arrayOfNulls(size)
        }
    }
}