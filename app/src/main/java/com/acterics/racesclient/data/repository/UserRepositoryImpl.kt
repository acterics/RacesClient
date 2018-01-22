package com.acterics.racesclient.data.repository

import android.content.Context
import com.acterics.domain.model.Bet
import com.acterics.domain.model.Token
import com.acterics.domain.model.User
import com.acterics.domain.model.dto.HistoryBet
import com.acterics.domain.model.dto.SignInCredentials
import com.acterics.domain.model.dto.SignUpCredentials
import com.acterics.domain.repository.UserRepository
import com.acterics.racesclient.common.extentions.*
import com.acterics.racesclient.data.database.AppDatabase
import com.acterics.racesclient.data.mapper.BetMapper
import com.acterics.racesclient.data.mapper.TokenMapper
import com.acterics.racesclient.data.mapper.UserMapper
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.model.BetModel
import com.acterics.racesclient.data.network.model.request.BetRequest
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import javax.inject.Inject

/**
 * Created by root on 30.10.17.
 */
class UserRepositoryImpl(private val appDatabase: AppDatabase,
                         private val apiService: ApiService,
                         private val context: Context,
                         private val userMapper: UserMapper,
                         private val tokenMapper: TokenMapper): UserRepository {



    override fun saveAuthenticateToken(token: Token): Completable =
        Completable.fromAction { context.saveToken(token) }


    override fun signInUser(signInCredentials: SignInCredentials): Single<Token> =
            apiService.signIn(signInCredentials)
                    .checkNetworkSingle()
                    .map { tokenMapper.toDomain(it) }

    override fun signUpUser(signUpCredentials: SignUpCredentials): Single<Token> =
            apiService.signUp(signUpCredentials)
                    .checkNetworkSingle()
                    .map { tokenMapper.toDomain(it) }


    override fun getUser(caching: Boolean): Single<User> =
            Single.just(context.getUser())
                    .onErrorResumeNext {
                        apiService.getUser()
                                .checkNetworkSingle()
                                .map { userMapper.toDomain(it) }
                    }
                    .doOnSuccess {
                        if (caching) {
                            context.saveUser(it)
                        }
                    }

    override fun deleteUser(): Completable =
        Completable.fromAction { context.clearUser() }



//    private fun cacheBetRequest(betModel: BetModel): Single<Bet> =
//        betModel.toSingle()
//                .doOnSuccess { appDatabase.betDao().insert(betMapper.toEntity(betModel)) }
//                .map { betMapper.toDomain(it) }

}