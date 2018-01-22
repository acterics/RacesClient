package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.User
import com.acterics.racesclient.data.network.model.UserModel
import javax.inject.Inject

/**
 * Created by oleg on 21.01.18.
 */
class UserMapper
@Inject constructor() {
    fun toDomain(from: UserModel): User {
        return User(
                from.id,
                from.firstName,
                from.lastName,
                from.email,
                from.details.avatar ?: ""
        )
    }

}