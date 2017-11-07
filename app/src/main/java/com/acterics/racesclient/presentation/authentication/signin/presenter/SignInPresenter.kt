package com.acterics.racesclient.presentation.authentication.signin.presenter

import android.content.Context
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.data.network.model.request.SignInRequest
import com.acterics.racesclient.domain.interactor.SignInUseCase
import com.acterics.racesclient.presentation.authentication.signin.view.SignInView
import com.acterics.racesclient.utils.validators.EmailValidator
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 29.09.17.
 */
@InjectViewState
class SignInPresenter(private val emailValidator: EmailValidator,
                      private val context: Context,
                      private val signInUseCase: SignInUseCase,
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
        //TODO add authorization logic
        signInUseCase
                .execute(params = SignInRequest(email, password),
                        onSuccess = { onSuccessLogin()},
                        onError = { viewState.showError(it.message) }
                )
    }


    private fun onSuccessLogin() {
        router.newRootScreen(Screens.MAIN)
    }





}