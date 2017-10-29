package com.acterics.racesclient.di.auth

import com.acterics.racesclient.presentation.authentication.AuthenticateActivity
import com.acterics.racesclient.presentation.authentication.signin.view.SignInFragment
import com.acterics.racesclient.presentation.authentication.signup.view.SignUpFragment
import dagger.Subcomponent

/**
 * Created by root on 29.10.17.
 */

@AuthenticationScope
@Subcomponent(modules = arrayOf(AuthenticationModule::class))
interface AuthenticationComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AuthenticationComponent
    }

    fun inject(authenticateActivity: AuthenticateActivity)
    fun inject(signInFragment: SignInFragment)
    fun inject(signUpFragment: SignUpFragment)
}