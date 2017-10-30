package com.acterics.racesclient.data.repository

import android.content.Context
import com.acterics.racesclient.common.extentions.getUser
import com.acterics.racesclient.data.database.AppDatabase
import com.acterics.racesclient.data.database.entity.User
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.domain.repository.UserRepository
import io.reactivex.Single

/**
 * Created by root on 30.10.17.
 */
class UserRepositoryImpl(private val appDatabase: AppDatabase,
                         private val apiService: ApiService,
                         private val context: Context): UserRepository {
    override fun addBet(bet: Float, rating: Float, participationId: Long, caching: Boolean): Single<Boolean> {
        //TODO implement
        return Single.just(true)
    }

    override fun getUser(): Single<User> {
        return Single.just(context.getUser())
    }

    override fun saveUser(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}