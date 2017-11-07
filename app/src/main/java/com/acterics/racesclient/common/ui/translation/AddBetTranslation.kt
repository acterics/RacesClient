package com.acterics.racesclient.common.ui.translation

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by root on 07.11.17.
 */
data class AddBetTranslation(
        var addBetHolder: String,
        var participantId: Long
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(addBetHolder)
        writeLong(participantId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AddBetTranslation> = object : Parcelable.Creator<AddBetTranslation> {
            override fun createFromParcel(source: Parcel): AddBetTranslation = AddBetTranslation(source)
            override fun newArray(size: Int): Array<AddBetTranslation?> = arrayOfNulls(size)
        }
    }
}