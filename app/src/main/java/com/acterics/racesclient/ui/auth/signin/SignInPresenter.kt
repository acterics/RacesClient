package com.acterics.racesclient.ui.auth.signin

import android.content.Context
import com.acterics.racesclient.R
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.login
import com.acterics.racesclient.utils.validators.EmailValidator
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

/**
 * Created by root on 29.09.17.
 */
@InjectViewState
class SignInPresenter: BaseNavigationPresenter<SignInView>() {

    @Inject
    lateinit var emailValidator: EmailValidator

    @Inject
    lateinit var context: Context

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

    fun onEmailInputChanged(email: CharSequence?) {
        if (email?.isEmpty() != true && !emailValidator.validate(email)) {
            viewState.showEmailInputError(R.string.error_invalid_email)
        } else {
            viewState.hideEmailInputError()
        }
    }


    fun onPasswordInputChanged(password: CharSequence?) {

    }

    fun onSignUpButtonClick() {
        router.navigateTo(Screens.SIGN_UP_SCREEN)
    }

    fun onSignInButtonClick() {
        //TODO add authorization logic
        context.login()
        router.newRootScreen(Screens.MAIN_SCREEN)
    }



}