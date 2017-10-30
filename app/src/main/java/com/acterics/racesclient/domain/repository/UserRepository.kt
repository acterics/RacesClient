package com.acterics.racesclient.domain.repository

import com.acterics.racesclient.data.database.entity.User
import io.reactivex.Single

/**
 * Created by root on 30.10.17.
 */
interface UserRepository {

    fun addBet(bet: Float, rating: Float,  participationId: Long, caching: Boolean): Single<Boolean>
    fun getUser(): Single<User>
    fun saveUser(): Single<Boolean>
}