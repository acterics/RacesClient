package com.acterics.racesclient.presentation.authentication.signin.presenter

import com.acterics.domain.interactor.AuthenticateInteractor
import com.acterics.domain.model.dto.SignInCredentials
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.presentation.authentication.signin.view.SignInView
import com.acterics.racesclient.utils.validators.EmailValidator
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

/**
 * Created by root on 29.09.17.
 */
@InjectViewState
class SignInPresenter(private val emailValidator: EmailValidator,
                      private val authenticateInteractor: AuthenticateInteractor,
                      private val router: Router): MvpPresenter<SignInView>() {


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
        router.navigateTo(Screens.SIGN_UP)
    }

    fun onSignInButtonClick(email: String, password: String) {
        authenticateInteractor.signIn(SignInCredentials(email, password))
                .subscribeBy(
                        onSuccess = { onSuccessLogin() },
                        onError = { error -> viewState.showError(error.message) }
                )
    }

    private fun onSuccessLogin() {
        router.newRootScreen(Screens.MAIN)
    }





}