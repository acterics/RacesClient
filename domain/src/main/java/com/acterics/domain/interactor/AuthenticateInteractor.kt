package com.acterics.domain.interactor

import com.acterics.domain.model.User
import com.acterics.domain.model.dto.SignInCredentials
import com.acterics.domain.model.dto.SignUpCredentials
import io.reactivex.Single

/**
 * Created by oleg on 21.01.18.
 */
interface AuthenticateInteractor: Interactor {

    fun signIn(signInCredentials: SignInCredentials): Single<User>
    fun signUp(signUpCredentials: SignUpCredentials): Single<User>
}