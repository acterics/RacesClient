package com.acterics.racesclient.data.model

import android.os.Parcel
import android.os.Parcelable
import com.acterics.racesclient.data.entity.User

/**
 * Created by root on 21.10.17.
 */
data class UserModel(val id: Long,
                     val firstName: String,
                     val lastName: String,
                     val email: String,
                     val userInfo: UserInfoModel): EntityWrapper<User> {

    override fun map(): User = User(id, firstName, lastName, email, userInfo.avatar)
}