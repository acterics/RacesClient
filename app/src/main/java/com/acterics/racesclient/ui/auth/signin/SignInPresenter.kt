package com.acterics.racesclient.ui.auth.signin

import com.acterics.racesclient.R
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.validators.EmailValidator
import javax.inject.Inject

/**
 * Created by root on 29.09.17.
 */
class SignInPresenter: BaseNavigationPresenter<SignInView>() {

    @Inject
    lateinit var emailValidator: EmailValidator

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


    fun onEmailInputChanged(email: CharSequence?) {
        if (email?.isEmpty() != true && emailValidator.validate(email)) {
            viewState.showEmailInputError(R.string.error_invalid_email)
        }
    }

    fun onPasswordInputChanged(password: CharSequence?) {

    }

    fun onSignUpButtonClick() {
//        router.navigateTo(Screens.SIGN_UP_SCREEN)
    }



}