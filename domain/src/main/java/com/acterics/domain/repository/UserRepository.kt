package com.acterics.domain.repository

import com.acterics.domain.model.Token
import com.acterics.domain.model.User
import com.acterics.domain.model.dto.SignInCredentials
import com.acterics.domain.model.dto.SignUpCredentials
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by root on 30.10.17.
 */
interface UserRepository {

    fun signInUser(signInCredentials: SignInCredentials): Single<Token>
    fun signUpUser(signUpCredentials: SignUpCredentials): Single<Token>
    fun getUser(caching: Boolean): Single<User>
    fun deleteUser(): Completable
    fun saveAuthenticateToken(token: Token): Completable
}