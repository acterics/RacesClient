package com.acterics.racesclient.data.network.model

/**
 * Created by root on 21.10.17.
 */
data class UserModel(val id: Long,
                     val firstName: String,
                     val lastName: String,
                     val email: String,
                     val userInfo: UserInfoModel)