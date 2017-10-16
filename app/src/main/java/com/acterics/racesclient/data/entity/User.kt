package com.acterics.racesclient.data.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by root on 09.10.17.
 */
data class User(val firstName: String,
                val lastName: String,
                val email: String,
                val avatar: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(firstName)
        writeString(lastName)
        writeString(email)
        writeString(avatar)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}