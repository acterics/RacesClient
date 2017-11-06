package com.acterics.racesclient.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 21.10.17.
 */
data class UserModel(
        val id: Long,

        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("last_name")
        val lastName: String,

        val email: String,
        val details: UserInfoModel)