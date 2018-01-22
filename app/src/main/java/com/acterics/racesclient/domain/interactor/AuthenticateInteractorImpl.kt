package com.acterics.racesclient.domain.interactor

import com.acterics.domain.interactor.AuthenticateInteractor
import com.acterics.domain.model.User
import com.acterics.domain.model.dto.SignInCredentials
import com.acterics.domain.model.dto.SignUpCredentials
import com.acterics.domain.repository.UserRepository
import com.acterics.racesclient.domain.executor.ExecutionScheduler
import io.reactivex.Single

/**
 * Created by oleg on 21.01.18.
 */
class AuthenticateInteractorImpl(private val userRepository: UserRepository,
                                 private val scheduler: ExecutionScheduler): AuthenticateInteractor {


    override fun signIn(signInCredentials: SignInCredentials): Single<User> {
        return with (userRepository) {
            deleteUser()
                    .andThen(signInUser(signInCredentials))
                    .flatMapCompletable { saveAuthenticateToken(it) }
                    .andThen(getUser(true))
                    .compose(scheduler.highPrioritySingle())
        }
    }

    override fun signUp(signUpCredentials: SignUpCredentials): Single<User> {
        return with (userRepository) {
            deleteUser()
                    .andThen(signUpUser(signUpCredentials))
                    .flatMapCompletable { saveAuthenticateToken(it) }
                    .andThen(getUser(true))
                    .compose(scheduler.highPrioritySingle())
        }
    }

}