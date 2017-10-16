package com.acterics.racesclient.data.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by root on 15.10.17.
 */
data class Organization(val name: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Organization> = object : Parcelable.Creator<Organization> {
            override fun createFromParcel(source: Parcel): Organization = Organization(source)
            override fun newArray(size: Int): Array<Organization?> = arrayOfNulls(size)
        }
    }
}